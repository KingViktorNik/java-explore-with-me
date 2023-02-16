package ru.practicum.ewm.admin.service.compilations;

import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.CompilationNewDto;
import ru.practicum.ewm.dto.compilation.CompilationUpdateDto;

public interface CompilationsAdminService {
    /**
     * Добавление новой подборки
     *
     * @param compilationNewDto новая подборка
     * @return CompilationDto
     **/
    CompilationDto addCompilation(CompilationNewDto compilationNewDto);

    /**
     * Изменение информацию о подборке
     *
     * @param compId               идентификатор категории
     * @param compilationUpdateDto новая подборка
     * @return CompilationDto
     **/
    CompilationDto updateCompilation(Long compId, CompilationUpdateDto compilationUpdateDto);

    /**
     * Удаление подборки
     *
     * @param compId идентификатор подборки
     **/
    void deleteCompilation(Long compId);

}
