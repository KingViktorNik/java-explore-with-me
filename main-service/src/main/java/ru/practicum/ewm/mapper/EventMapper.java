package ru.practicum.ewm.mapper;

import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.dto.event.EventShortDto.*;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventNewDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;
import ru.practicum.ewm.model.event.EventLocation;
import ru.practicum.ewm.model.event.EventShort;
import ru.practicum.ewm.util.DateTimeConverter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class EventMapper {
    public static Event toEntityNew(EventNewDto dto) {
        return Event.builder()
                .annotation(dto.getAnnotation())
                .category(new Category(dto.getCategory(), null))
                .description(dto.getDescription())
                .eventDate(DateTimeConverter.toDateTime(dto.getEventDate()))
                .eventLocation(new EventLocation(dto.getLocation().getLat(), dto.getLocation().getLon()))
                .paid(dto.getPaid())
                .participantLimit(dto.getParticipantLimit())
                .requestModeration(dto.getRequestModeration())
                .title(dto.getTitle())
                .build();
    }

    public static Event toEntityUpdate(EventUpdateDto dto, Event ev) {
        ev.setAnnotation(dto.getAnnotation() == null
                ? ev.getAnnotation()
                : dto.getAnnotation());
        ev.setCategory(dto.getCategory() == null || dto.getCategory() .equals(ev.getCategory().getId())
                ? ev.getCategory()
                : new Category(dto.getCategory(), null));
        ev.setDescription(dto.getDescription() == null
                ? ev.getDescription()
                : dto.getDescription());
        ev.setEventDate( dto.getEventDate()   == null
                ? ev.getEventDate()
                : DateTimeConverter.toDateTime(dto.getEventDate()));
        ev.setEventLocation( dto.getEventLocation() == null
                ? ev.getEventLocation()
                : new EventLocation(dto.getEventLocation().getLat() == null
                                        ? ev.getEventLocation().getLat()
                                        : dto.getEventLocation().getLat(),
                                    dto.getEventLocation().getLon() == null
                                        ? ev.getEventLocation().getLon()
                                        : dto.getEventLocation().getLon()
                )
        );
        ev.setPaid(dto.getPaid() == null
                ? ev.getPaid()
                : dto.getPaid());
        ev.setParticipantLimit(dto.getParticipantLimit() == null
                ? ev.getParticipantLimit()
                : dto.getParticipantLimit());
        ev.setRequestModeration(dto.getRequestModeration() == null
                ? ev.getRequestModeration()
                : dto.getRequestModeration());
        ev.setTitle(dto.getTitle() == null
                ? ev.getTitle()
                : dto.getTitle());
        return ev;
    }

    public static EventFullDto toFullDto(Event ev) {
        return EventFullDto.builder()
                .annotation(ev.getAnnotation())
                .category(new EventFullDto.Category(ev.getCategory().getId(), ev.getCategory().getName()))
                .confirmedRequests(ev.getConfirmedRequests() == null ? 0 : ev.getConfirmedRequests())
                .createdOn(DateTimeConverter.toString(ev.getCreatedOn()))
                .description(ev.getDescription())
                .eventDate(DateTimeConverter.toString(ev.getEventDate()))
                .id(ev.getId())
                .initiator(new EventFullDto.UserShort(ev.getInitiator().getId(),
                        ev.getInitiator().getName()))
                .location(new EventFullDto.Location(ev.getEventLocation().getLat(), ev.getEventLocation().getLon()))
                .paid(ev.getPaid())
                .participantLimit(ev.getParticipantLimit())
                .publishedOn(DateTimeConverter.toString(ev.getCreatedOn()))
                .requestModeration(ev.getRequestModeration())
                .state(ev.getState())
                .title(ev.getTitle())
                .views(ev.getViews() == null ? 0 : ev.getViews())
                .build();
    }

    public static EventShortDto toShortDto(EventShort ev) {
        return EventShortDto.builder()
                .annotation(ev.getAnnotation())
                .category(new EventShortDto.Category(ev.getCategory().getId(), ev.getCategory().getName()))
                .confirmedRequests(ev.getConfirmedRequests() == null ? 0 : ev.getConfirmedRequests())
                .eventDate(DateTimeConverter.toString(ev.getEventDate()))
                .id(ev.getId())
                .initiator(new EventShortDto.UserShort(ev.getInitiator().getId(), ev.getInitiator().getName()))
                .paid(ev.getPaid())
                .title(ev.getTitle())
                .views(ev.getViews() == null ? 0 : ev.getViews())
                .build();
    }

    public static EventShortDto toShortDto(Event ev) {
        return EventShortDto.builder()
                .annotation(ev.getAnnotation())
                .category(new EventShortDto.Category(ev.getCategory().getId(), ev.getCategory().getName()))
                .confirmedRequests(ev.getConfirmedRequests() == null ? 0 : ev.getConfirmedRequests())
                .eventDate(DateTimeConverter.toString(ev.getEventDate()))
                .id(ev.getId())
                .initiator(new UserShort(ev.getInitiator().getId(), ev.getInitiator().getName()))
                .paid(ev.getPaid())
                .title(ev.getTitle())
                .views(ev.getViews() == null ? 0 : ev.getViews())
                .build();
    }

    public static List<EventShortDto> toShortDtos(List<Event> events) {
        return events.stream()
                .map(EventMapper::toShortDto)
                .collect(toList());
    }
}
