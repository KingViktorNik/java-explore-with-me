package ru.practicum.ewm.publics.service.compilations;

import ru.practicum.ewm.dto.compilation.CompilationDto;

import java.util.List;

public interface CompilationsPublicService {
    /** Получение подборок событий
     *
     * @param pinned  закрепленные подборки
     * @param from    PageRequest страница
     * @param size    PageRequest количество элементов
     * @return List < CompilationDto >
     * **/
    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    /** Получение подборки событий по его id
     *
     * @param compId идентификатор подборки
     * @return CompilationDto
     * **/
    CompilationDto getCompilation(Long compId);
}
