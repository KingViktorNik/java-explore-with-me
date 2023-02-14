package ru.practicum.ewm.mapper;


import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.CompilationNewDto;
import ru.practicum.ewm.dto.compilation.CompilationUpdateDto;
import ru.practicum.ewm.model.Compilation;


import java.util.Set;

import static java.util.stream.Collectors.toList;

public class CompilationMapper {
    public static Compilation toEntityNew(CompilationNewDto dto) {
        return new Compilation(
                Set.of(),
                null,
                dto.getPinned(),
                dto.getTitle()
        );
    }

    public static CompilationDto toDto(Compilation entity) {
        return new CompilationDto(
                entity.getCompilationEvents().stream().map(EventMapper::toShortDto).collect(toList()),
                entity.getId(),
                entity.getPinned(),
                entity.getTitle()
        );
    }

    public static Compilation toEntityUpdate(CompilationUpdateDto dto, Compilation entityBefore) {
        return new Compilation(
                Set.of(),
                entityBefore.getId(),
                dto.getPinned() == null ? entityBefore.getPinned() : dto.getPinned(),
                dto.getTitle() == null ? entityBefore.getTitle() : dto.getTitle()
        );
    }
}
