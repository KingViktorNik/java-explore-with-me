package ru.practicum.ewm.publics.service.compilations;

import ru.practicum.ewm.dto.compilation.CompilationDto;

import java.util.List;

public interface CompilationsPublicService {
    /** Получение подборок событий **/
    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    /** Получение подборки событий по его id **/
    CompilationDto getCompilation(Long compId);
}
