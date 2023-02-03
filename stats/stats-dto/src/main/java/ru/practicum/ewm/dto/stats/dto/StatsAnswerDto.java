package ru.practicum.ewm.dto.stats.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatsAnswerDto implements Serializable {
    private String app;
    private String uri;
    private Integer hits;
}
