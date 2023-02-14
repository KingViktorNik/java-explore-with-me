package ru.practicum.ewm.dto.event;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventShortDto {
    /**
     * Краткое описание
     **/
    private String annotation;

    /**
     * Категория
     **/
    private Category category;

    /**
     * Количество одобренных заявок на участие в данном событии
     **/
    private Integer confirmedRequests;

    /**
     * Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
     **/
    private String eventDate;

    /**
     * Идентификатор
     **/
    private Long id;

    /**
     * Пользователь (краткая информация)
     **/
    private UserShort initiator;

    /**
     * Нужно ли оплачивать участие
     **/
    private Boolean paid;

    /**
     * Заголовок
     **/
    private String title;

    /**
     * Количество просмотрев события
     **/
    private Integer views;

    @Getter
    @Setter
    @AllArgsConstructor
    public static final class UserShort {
        /**
         * Идентификатор
         **/
        private Long id;

        /**
         * Имя пользователя
         **/
        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static final class Category {
        /**
         * Идентификатор
         **/
        private Long id;

        /**
         * Название
         **/
        private String name;
    }
}
