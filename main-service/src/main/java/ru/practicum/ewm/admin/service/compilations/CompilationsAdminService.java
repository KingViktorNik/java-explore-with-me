package ru.practicum.ewm.admin.service.compilations;

import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.CompilationNewDto;
import ru.practicum.ewm.dto.compilation.CompilationUpdateDto;

public interface CompilationsAdminService {
    /**
     * Добавление новой подборки
     **/
    CompilationDto addCompilation(CompilationNewDto compilationNewDto);

    /**
     * Обновить информацию о подборке
     **/
    CompilationDto updateCompilation(Long compId, CompilationUpdateDto compilationUpdateDto);

    /**
     * Удаление подборки
     **/
    void deleteCompilation(Long compId);
}
