package ru.practicum.ewm.model;

import lombok.*;
import ru.practicum.ewm.model.event.Event;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "compilations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compilation {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "compilation_events", joinColumns = @JoinColumn(name = "compilations_id"),
    inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> compilationEvents;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pinned")
    private Boolean pinned;

    @Column(name = "title", length = 100, nullable = false)
    private String title;
}
