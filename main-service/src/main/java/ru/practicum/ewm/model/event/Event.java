package ru.practicum.ewm.model.event;

import lombok.*;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.model.User;
import ru.practicum.ewm.model.event.enums.EventState;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "events")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    /** Идентификатор события **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** пользователь (инициатор) **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User initiator;

    /** Краткое описание события **/
    @Column(name = "annotation", length = 2000, nullable = false)
    private String annotation;

    /** Категория к которой относится событие **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /** Количество одобренных заявок **/
    @Column(name = "confirmed_requests")
    private Integer confirmedRequests;

    /** Дата и время создания события **/
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    /** Полное описание события **/
    @Column(name = "description", length = 7000, nullable = false)
    private String description;

    /** Дата и время на которые намечено событие. **/
    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    /** Место проведения события **/
    @Embedded
    private EventLocation eventLocation;

    /** Оплата участие в событии **/
    @Column(name = "paid")
    private Boolean paid;

    /** Ограничение на количество участников. **/
    @Column(name = "participant_limit")
    private Integer participantLimit;

    /** Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss") **/
    @Column(name = "published_on")
    private LocalDateTime publishedOn;


    /** Доступ для участия. **/
    @Column(name = "available")
    private Boolean available;

    /** Модерация заявок на участие **/
    @Column(name = "request_moderation")
    private Boolean requestModeration;

    /** Заголовок события **/
    @Column(name = "title", length = 120, nullable = false)
    private String title;

    /** Состояний жизненного цикла события (PENDING, PUBLISHED, CANCELED) **/
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private EventState state;

    /** Количество просмотров события **/
    @Column(name = "views")
    private Integer views;
}
