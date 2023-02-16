package ru.practicum.ewm.dto.event;

import lombok.*;
import ru.practicum.ewm.model.event.enums.EventState;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventFullDto {
    /** Краткое описание **/
    private String annotation;

    /** Категория **/
    private Category category;

    /** Количество одобренных заявок на участие в данном событии **/
    private Integer confirmedRequests;

    /** Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss") **/
    private String createdOn;

    /** Полное описание события **/
    private String description;

    /** Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss") **/
    private String eventDate;

    /** Идентификатор **/
    private Long id;

    /** Пользователь (краткая информация) **/
    private UserShort initiator;

    /** Места проведения события **/
    private Location location;

    /** Нужно ли оплачивать участие **/
    private Boolean paid;

    /** Ограничение на количество участников **/
    private Integer participantLimit;

    /** Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss") **/
    private String publishedOn;

    /** Нужна ли пре-модерация заявок на участие **/
    private Boolean requestModeration;

    /** Список состояний жизненного цикла события **/
    private EventState state;

    /** Заголовок **/
    private String title;

    /** Количество просмотрев события **/
    private Integer views;

    @Getter
    @Setter
    @AllArgsConstructor
    public static final class UserShort {
        /** Идентификатор **/
        private Long id;

        /** Имя пользователя **/
        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static final class Category {
        /** Идентификатор **/
        private Long id;

        /** Название **/
        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static final class Location {
        /** Широта **/
        private Float lat;

        /** Долгота **/
        private Float lon;
    }

}
