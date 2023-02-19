package ru.practicum.ewm.dto.stats.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EndpointHitDto implements Serializable {
    private Long id;

    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String app;

    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String uri;

    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String ip;

    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String timestamp;

    public EndpointHitDto(String uri, String ip) {
        this.uri = uri;
        this.ip = ip;
    }
}
