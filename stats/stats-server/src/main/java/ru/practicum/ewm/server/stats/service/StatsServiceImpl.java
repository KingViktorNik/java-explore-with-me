package ru.practicum.ewm.server.stats.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.stats.dto.EndpointHitDto;
import ru.practicum.ewm.dto.stats.dto.StatsAnswerDto;
import ru.practicum.ewm.dto.stats.util.DateTimeConverter;
import ru.practicum.ewm.server.stats.mapper.StatsAnswerMapper;
import ru.practicum.ewm.server.stats.mapper.EndpointHitMapper;
import ru.practicum.ewm.server.stats.model.EndpointHit;
import ru.practicum.ewm.server.stats.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository repository;

    @Override
    public EndpointHitDto saveHit(EndpointHitDto endpointHitDto) {
        EndpointHit result = EndpointHitMapper.toEntity(endpointHitDto);
        EndpointHit endpointHit = repository.save(result);
        log.info(" saveHit id:{} ip:{}", endpointHitDto.getId(),endpointHitDto.getIp());

        return EndpointHitMapper.toDto(endpointHit);

    }

    @Override
    public List<StatsAnswerDto> getStats(String startSt, String endSt, Set<String> uris, Boolean unique) {
        LocalDateTime start = DateTimeConverter.toDateTime(startSt);
        LocalDateTime end = DateTimeConverter.toDateTime(endSt);
        List<StatsAnswerDto> result;

        if (unique) {
            result =  repository.findByUriUnique(start, end, uris)
                                .stream()
                                .map(StatsAnswerMapper::toDto)
                                .sorted((o1, o2) -> o2.getHits().compareTo(o1.getHits()))
                                .collect(toList());
        } else {
            result =  repository.findByUri(start, end, uris)
                                .stream()
                                .map(StatsAnswerMapper::toDto)
                                .sorted((o1, o2) -> o2.getHits().compareTo(o1.getHits()))
                                .collect(toList());
        }

        log.info("getStats listSize:{}", result.size());

        return result;

    }

}
