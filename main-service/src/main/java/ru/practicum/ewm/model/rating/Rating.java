package ru.practicum.ewm.model.rating;

import lombok.*;
import ru.practicum.ewm.model.User;
import ru.practicum.ewm.model.event.Event;

import javax.persistence.*;

@Entity
@Table(name = "rating_events")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    /** Идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Пользователь */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** Событие */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    /** Оценка пользователя */
    @Column(name = "like_event", nullable = false)
    private Boolean likeEvent;

}
