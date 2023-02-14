package ru.practicum.ewm.dto.event;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class EventNewDto {
    /**
     * Краткое описание события
     */
    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String annotation;

    /**
     * id категории к которой относится событие
     */
    @NotNull(message = "can not be null")
    private Long category;

    /**
     * Полное описание события
     */
    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String description;

    /**
     * Дата и время на которые намечено событие. Дата и время указываются в формате "yyyy-MM-dd HH:mm:ss"
     */
    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String eventDate;

    /**
     * Места проведения события
     */
    private Location location;

    /**
     * Нужно ли оплачивать участие в событии
     */
    @NotNull(message = "can not be null")
    private Boolean paid;

    /**
     * Ограничение на количество участников.
     *
     * @default: 0 - означает отсутствие ограничения
     **/
    @NotNull(message = "can not be null")
    private Integer participantLimit;

    /**
     * Нужна ли пре-модерация заявок на участие.
     * Если true, то все заявки будут ожидать подтверждения инициатором события.
     * Если false - то будут подтверждаться автоматически.
     **/
    @NotNull(message = "can not be null")
    private Boolean requestModeration;

    /**
     * Заголовок события
     */
    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String title;

    @Getter
    @Setter
    @AllArgsConstructor
    public static final class Location {
        /**
         * широта
         **/
        private Float lat;

        /**
         * долгота
         **/
        private Float Lon;
    }

}
