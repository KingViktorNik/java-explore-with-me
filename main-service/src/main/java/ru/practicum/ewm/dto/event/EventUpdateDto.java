package ru.practicum.ewm.dto.event;

import lombok.*;
import ru.practicum.ewm.model.event.EventLocation;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateDto {
    /**
     * Краткое описание
     **/
    private String annotation;

    /**
     * id категории к которой относится событие
     */
    private Long category;

    /**
     * Полное описание события
     **/
    private String description;

    /**
     * Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
     **/
    private String eventDate;

    /**
     * Места проведения события
     **/
    private EventLocation eventLocation;

    /**
     * Нужно ли оплачивать участие
     **/
    private Boolean paid;

    /**
     * Ограничение на количество участников.
     *
     * @default: 0 - означает отсутствие ограничения
     **/
    private Integer participantLimit;

    /**
     * Нужна ли пре-модерация заявок на участие
     *
     * @default: true
     **/
    private Boolean requestModeration;

    /**
     * Состояния события
     **/
    private String stateAction;

    /**
     * Заголовок события
     */
    private String title;
}