package ru.practicum.ewm.dto.compilation;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
public class CompilationNewDto {
    @NotNull(message = "can not be null")
    private Set<Long> events;
    @NotNull(message = "can not be null")
    private Boolean pinned;

    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String title;
}
