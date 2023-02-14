package ru.practicum.ewm.dto.compilation;

import lombok.Getter;

import java.util.Set;

@Getter
public class CompilationUpdateDto {
    private Set<Long> events;

    private Boolean pinned;

    private String title;
}
