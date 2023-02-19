package ru.practicum.ewm.publics.service.event;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.client.stats.StatsClient;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortInquiryDto;
import ru.practicum.ewm.dto.stats.dto.EndpointHitDto;
import ru.practicum.ewm.mapper.EventMapper;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.repository.EventRepository;
import ru.practicum.ewm.util.QPredicates;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.practicum.ewm.model.event.QEvent.event;
import static ru.practicum.ewm.model.event.enums.EventState.PUBLISHED;

@Service
@AllArgsConstructor
public class EventPublicServiceImpl implements EventPublicService {
    private final EventRepository repository;
    private final StatsClient client;

    @Override
    public List<ru.practicum.ewm.dto.event.EventShortDto> getEvents(EventShortInquiryDto dto, HttpServletRequest request) {
        Predicate predicate = QPredicates.builder()
                                         .add(PUBLISHED, event.state::eq)
                                         .add(dto.getText(), event.annotation::containsIgnoreCase)
                                         .add(dto.getText(), event.description::containsIgnoreCase)
                                         .add(dto.getCategories(), event.category.id::in)
                                         .add(dto.getPaid(), event.paid::eq)
                                         .add(dto.getStart(), event.eventDate::after)
                                         .add(dto.getEnd(), event.eventDate::before)
                                         .add(dto.getOnlyAvailable(), event.available::eq)
                                         .buildOr();

        List<Event> result = repository.findAll(predicate,
                                                PageRequest.of(dto.getFrom(),
                                                dto.getSize()))
                                       .stream()
                                       .collect(toList());

        List<EndpointHitDto> hitDtos = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setViews(result.get(i).getViews() + 1);
            EndpointHitDto hitDto = new EndpointHitDto(request.getRequestURI() + "/" + result.get(i).getId(),
                                                           request.getRemoteAddr());
            hitDtos.add(hitDto);
        }

        client.saveHit(new EndpointHitDto(request.getRequestURI(), request.getRemoteAddr()));

        if (!result.isEmpty()) {
            repository.saveAll(result);
            client.saveHits(hitDtos);
        }

        return result.stream()
                .map(EventMapper::toShortDto)
                .sorted((o1, o2) -> {
                            if (dto.getSort().equals(EventShortInquiryDto.EventSort.VIEWS)) {
                                return o2.getViews().compareTo(o1.getViews());
                            } else {
                                return o2.getEventDate().compareTo(o1.getEventDate());
                            }
                        }
                )
                .collect(toList());

    }

    @Override
    public EventFullDto getEvent(Long id, HttpServletRequest request) {
        Event event = repository.findByIdAndState(id, PUBLISHED)
                                .orElseThrow(() -> new NullPointerException("Event with id=" + id + " was not found"));
        event.setViews(event.getViews() + 1);
        repository.save(event);
        EndpointHitDto hitDto = new EndpointHitDto(request.getRequestURI(),
                                                   request.getRemoteAddr());
        client.saveHit(hitDto);

        return EventMapper.toFullDto(event);

    }

}
