package ru.practicum.ewm.model.event;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EventLocation {
    /** Широта места проведения события **/
    @Column(name = "location_lat", nullable = false)
    private Float lat;

    /** Долгота места проведения события **/
    @Column(name = "location_lon", nullable = false)
    private Float lon;

}
