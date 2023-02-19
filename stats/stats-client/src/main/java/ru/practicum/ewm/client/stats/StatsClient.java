package ru.practicum.ewm.client.stats;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.dto.stats.dto.EndpointHitDto;
import ru.practicum.ewm.dto.stats.dto.StatsAnswerDto;
import ru.practicum.ewm.dto.stats.util.DateTimeConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StatsClient {
    private final String dateTime = DateTimeConverter.toString(LocalDateTime.now());
    private final String app;
    private final RestTemplate template;

    public StatsClient(@Value("${spring.application.name}") String application,
                       @Value("${stats-server}") String server) {
        this.app = application;
        template = new RestTemplate();
        template.setUriTemplateHandler(new DefaultUriBuilderFactory(server));
    }

    public ResponseEntity<Void> saveHit(EndpointHitDto endpointHitDto) {
        endpointHitDto.setApp(app);
        endpointHitDto.setTimestamp(dateTime);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EndpointHitDto> request = new HttpEntity<>(endpointHitDto, headers);
        ResponseEntity<EndpointHitDto> response = template.exchange("/hit", HttpMethod.POST, request, EndpointHitDto.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("[HIT] POST saveHit statusCode: {}", response.getStatusCode());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.debug("[HIT] POST stats_saveHit statusCode: {}", response.getStatusCode());
            throw new RuntimeException();
        }
    }

    public void saveHits(List<EndpointHitDto> dtos) {
        for (EndpointHitDto hitDto : dtos) {
            saveHit(hitDto);
        }
    }

    public List<StatsAnswerDto> getStats(String start, String end, List<String> uris, Boolean unique) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));


        Map<String, ?> parameters = parameters(start, end, uris, unique);
        final String path = "?start={start}&end={end}&uris={uris}&unique={unique}";

        HttpEntity<List<StatsAnswerDto>> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<StatsAnswerDto>> response = template.exchange(
                "/stats" + path,
                HttpMethod.GET, request,
                new ParameterizedTypeReference<>() {
                },
                parameters
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("[STATS] GET statusCode: {}", response.getStatusCode());
            return response.getBody();
        } else {
            log.debug("[STATS] GET statusCode: {}", response.getStatusCode());
            throw new RuntimeException();
        }

    }

    private static Map<String, ?> parameters(String start, String end, List<String> uris, Boolean unique) {
        String urisSt = uris.stream()
                .map(uri -> uri.equals(uris.get(uris.size() - 1)) ? uri : uri + ",")
                .collect(Collectors.joining());
        return Map.of(
                "start", start,
                "end", end,
                "uris", urisSt,
                "unique", unique
        );
    }
}
