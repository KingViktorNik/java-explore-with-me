package ru.practicum.ewm.publics.service.event;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.client.stats.StatsClient;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortInquiryDto;
import ru.practicum.ewm.dto.stats.dto.EndpointHitDto;
import ru.practicum.ewm.mapper.EventMapper;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.event.enums.EventState;
import ru.practicum.ewm.repository.EventRepository;
import ru.practicum.ewm.util.QPredicates;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.practicum.ewm.model.event.QEvent.event;

@Slf4j
@Service
@AllArgsConstructor
public class EventPublicServiceImpl implements EventPublicService {
    private final EventRepository repository;
    private final StatsClient client;

    @Override
    public List<ru.practicum.ewm.dto.event.EventShortDto> getEvents(EventShortInquiryDto dto) {
        Predicate predicate = QPredicates.builder()
                .add(EventState.PUBLISHED, event.state::eq)
                .add(dto.getText(), event.annotation::containsIgnoreCase)
                .add(dto.getText(), event.description::containsIgnoreCase)
                .add(dto.getCategories(), event.category.id::in)
                .add(dto.getPaid(), event.paid::eq)
                .add(dto.getRange().getStart(), event.eventDate::after)
                .add(dto.getRange().getEnd(), event.eventDate::before)
                .add(dto.getOnlyAvailable(), event.available::eq)
                .buildOr();

        List<Event> result = repository.findAll(predicate, PageRequest.of(dto.getPage().getFrom(),
                dto.getPage().getSize())).stream().collect(toList());

        List<EndpointHitDto> hitDtos = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setViews(result.get(i).getViews() + 1);
            EndpointHitDto hitDto = new EndpointHitDto("/events/" + result.get(i).getId(), "IP");
            hitDtos.add(hitDto);
        }
        client.saveHit(new EndpointHitDto("/events", "IP"));

        if (!result.isEmpty()) {
            repository.saveAll(result);
            client.saveHits(hitDtos);
        }

        return result.stream()
                .map(EventMapper::toShortDto)
                .sorted(((o1, o2) -> {
                    if (dto.getSort().equals(EventShortInquiryDto.EventSort.VIEWS)) {
                        return o2.getViews().compareTo(o1.getViews());
                    } else {
                        return o2.getEventDate().compareTo(o1.getEventDate());
                    }
                }))
                .collect(toList());
    }

    @Override
    public EventFullDto getEvent(Long id) {
        Event event = repository.findByIdAndState(id, EventState.PUBLISHED)
                .orElseThrow(() -> new NullPointerException("Event with id=" + id + " was not found"));
        event.setViews(event.getViews() + 1);
        repository.save(event);
        EndpointHitDto hitDto = new EndpointHitDto("/events/" + event.getId(), "IP");
        client.saveHit(hitDto);
        return EventMapper.toFullDto(event);
    }
}
