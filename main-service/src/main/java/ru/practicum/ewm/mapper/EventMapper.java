package ru.practicum.ewm.mapper;

import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventFullDto.*;
import ru.practicum.ewm.dto.event.EventNewDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;
import ru.practicum.ewm.model.event.EventLocation;
import ru.practicum.ewm.model.event.EventShort;
import ru.practicum.ewm.util.DateTimeConverter;

import java.time.LocalDateTime;

public class EventMapper {
    public static Event toEntityNew(EventNewDto dto) {
        // Категория к которой относится событие
        Category category = new Category(dto.getCategory(), null);

        // Дата и время на которые намечено событие.
        LocalDateTime eventDate = null;
        if (dto.getEventDate() != null) {
            eventDate = DateTimeConverter.toDateTime(dto.getEventDate());
        }

        // Место проведения события
        EventLocation eventLocation = new EventLocation(dto.getLocation().getLat(),
                                                        dto.getLocation().getLon());

        return Event.builder()
                    .annotation(dto.getAnnotation())
                    .category(category)
                    .description(dto.getDescription())
                    .eventDate(eventDate)
                    .eventLocation(eventLocation)
                    .paid(dto.getPaid())
                    .participantLimit(dto.getParticipantLimit())
                    .requestModeration(dto.getRequestModeration())
                    .title(dto.getTitle())
                    .build();
    }

    public static Event toEntityUpdate(EventUpdateDto dto, Event event) {
        // Краткое описание события
        if ((dto.getAnnotation() != null) && !dto.getAnnotation().isEmpty()) {
            event.setAnnotation(dto.getAnnotation());
        }

        // категория к которой относится событие
        if ((dto.getCategory() != null) && !dto.getCategory().equals(event.getCategory().getId())) {
            Category category = new Category(dto.getCategory(), null);
            event.setCategory(category);
        }

        // Полное описание события
        if (dto.getDescription() != null) {
            event.setDescription(dto.getDescription());
        }

        // Дата и время на которые намечено событие.
        if (dto.getEventDate() != null) {
            LocalDateTime eventDate = DateTimeConverter.toDateTime(dto.getEventDate());
            event.setEventDate(eventDate);
        }

        // Место проведения события
        if (dto.getEventLocation() != null) {
            EventLocation location = new EventLocation(dto.getEventLocation().getLat(),
                                                       dto.getEventLocation().getLon());
            event.setEventLocation(location);
        }

        // Оплата участие в событии
        if (dto.getPaid() != null) {
            event.setPaid(dto.getPaid());
        }

        // Ограничение на количество участников.
        if (dto.getParticipantLimit() != null) {
            event.setParticipantLimit(dto.getParticipantLimit());
        }

        // Модерация заявок на участие
        if (dto.getRequestModeration() != null) {
            event.setRequestModeration(dto.getRequestModeration());
        }

        // Заголовок события
        if (dto.getTitle() != null) {
            event.setTitle(dto.getTitle());
        }

        return event;

    }

    public static EventFullDto toFullDto(Event event) {
        // Категория к которой относится событие
        EventFullDto.Category category = new EventFullDto.Category(event.getCategory().getId(),
                                                                   event.getCategory().getName());
        //  Количество одобренных заявок
        int confirmedRequests = 0;
        if (event.getConfirmedRequests() != null) {
            confirmedRequests = event.getConfirmedRequests();
        }

        // Дата и время создания события
        String createdOn = null;
        if (event.getCreatedOn() == null) {
            createdOn = DateTimeConverter.toString(event.getCreatedOn());
        }

        // Дата и время на которые намечено событие.
        String eventDate = null;
        if (event.getEventDate() != null) {
            eventDate = DateTimeConverter.toString(event.getEventDate());
        }

        // Пользователь (инициатор)
        EventFullDto.UserShort userShort = new EventFullDto.UserShort(event.getInitiator().getId(),
                                                                      event.getInitiator().getName());
        // Место проведения события
        Location location = new Location(event.getEventLocation().getLat(),
                                         event.getEventLocation().getLon());

        // Дата и время публикации события
        String publishedOn = null;
        if (event.getPublishedOn() != null) {
            publishedOn = DateTimeConverter.toString(event.getPublishedOn());
        }

        // Количество просмотров события
        int views = 0;
        if (event.getViews() != null) {
            views = event.getViews();
        }

        return EventFullDto.builder()
                           .annotation(event.getAnnotation())
                           .category(category)
                           .confirmedRequests(confirmedRequests)
                           .createdOn(createdOn)
                           .description(event.getDescription())
                           .eventDate(eventDate)
                           .id(event.getId())
                           .initiator(userShort)
                           .location(location)
                           .paid(event.getPaid())
                           .participantLimit(event.getParticipantLimit())
                           .publishedOn(publishedOn)
                           .requestModeration(event.getRequestModeration())
                           .state(event.getState())
                           .title(event.getTitle())
                           .views(views)
                           .build();

    }

    public static EventShortDto toShortDto(EventShort eventShort) {
        // Категория к которой относится событие
        EventShortDto.Category category = new EventShortDto.Category(eventShort.getCategory().getId(),
                                                                     eventShort.getCategory().getName());

        // Количество одобренных заявок
        Integer confirmedRequests = 0;
        if (eventShort.getConfirmedRequests() != null) {
            confirmedRequests = eventShort.getConfirmedRequests();
        }
        // Дата и время на которые намечено событие.
        String eventDate = null;
        if (eventShort.getEventDate() != null) {
            eventDate = DateTimeConverter.toString(eventShort.getEventDate());
        }

        // Пользователь (инициатор)
        EventShortDto.UserShort initiator = new EventShortDto.UserShort(eventShort.getInitiator().getId(),
                                                                        eventShort.getInitiator().getName());

        // Количество просмотров события
        int views = 0;
        if (eventShort.getViews() != null) {
            views = eventShort.getViews();
        }

        return EventShortDto.builder()
                            .annotation(eventShort.getAnnotation())
                            .category(category)
                            .confirmedRequests(confirmedRequests)
                            .eventDate(eventDate)
                            .id(eventShort.getId())
                            .initiator(initiator)
                            .paid(eventShort.getPaid())
                            .title(eventShort.getTitle())
                            .views(views)
                            .build();

    }

    public static EventShortDto toShortDto(Event event) {
        // Категория к которой относится событие
        EventShortDto.Category category = new EventShortDto.Category(event.getCategory().getId(),
                                                                     event.getCategory().getName());

        // Количество одобренных заявок
        Integer confirmedRequests = 0;
        if (event.getConfirmedRequests() != null) {
            confirmedRequests = event.getConfirmedRequests();
        }
        // Дата и время на которые намечено событие.
        String eventDate = null;
        if (event.getEventDate() != null) {
            eventDate = DateTimeConverter.toString(event.getEventDate());
        }

        // Пользователь (инициатор)
        EventShortDto.UserShort initiator = new EventShortDto.UserShort(event.getInitiator().getId(),
                event.getInitiator().getName());

        // Количество просмотров события
        int views = 0;
        if (event.getViews() != null) {
            views = event.getViews();
        }

        return EventShortDto.builder()
                            .annotation(event.getAnnotation())
                            .category(category)
                            .confirmedRequests(confirmedRequests)
                            .eventDate(eventDate)
                            .id(event.getId())
                            .initiator(initiator)
                            .paid(event.getPaid())
                            .title(event.getTitle())
                            .views(views)
                            .build();

    }

}
