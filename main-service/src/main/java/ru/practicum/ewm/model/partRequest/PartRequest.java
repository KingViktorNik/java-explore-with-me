package ru.practicum.ewm.model.partRequest;

import lombok.*;
import ru.practicum.ewm.model.partRequest.enums.RequestStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participation_request")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartRequest {
    /** Идентификатор заявки **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Дата и время создания заявки **/
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    /** Идентификатор события **/
    @Column(name = "event_id")
    private Long eventId;

    /** Идентификатор пользователя, отправившего заявку **/
    @Column(name = "requester_id")
    private Long requesterId;

    /** Статус заявки **/
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
