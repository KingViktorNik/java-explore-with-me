package ru.practicum.ewm.server.stats.service;

import lombok.AllArgsConstructor;
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

@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository repository;

    @Override
    public EndpointHitDto saveHit(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = repository.save(EndpointHitMapper.toEntity(endpointHitDto));
        return EndpointHitMapper.toDto(endpointHit);
    }

    @Override
    public List<StatsAnswerDto> getStats(String startSt, String endSt, Set<String> uris, Boolean unique) {
        LocalDateTime start = DateTimeConverter.toDateTime(startSt);
        LocalDateTime end = DateTimeConverter.toDateTime(endSt);
        if (unique) {
            return repository.findByUriUnique(start, end, uris).stream()
                    .map(StatsAnswerMapper::toDto)
                    .sorted((o1, o2) -> o2.getHits().compareTo(o1.getHits()))
                    .collect(toList());
        } else {
            return repository.findByUri(start, end, uris).stream()
                    .map(StatsAnswerMapper::toDto)
                    .sorted((o1, o2) -> o2.getHits().compareTo(o1.getHits()))
                    .collect(toList());
        }
    }
}
