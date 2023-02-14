package ru.practicum.ewm.admin.service.compilations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.CompilationNewDto;
import ru.practicum.ewm.dto.compilation.CompilationUpdateDto;
import ru.practicum.ewm.exception.NullObjectException;
import ru.practicum.ewm.model.Compilation;
import ru.practicum.ewm.mapper.CompilationMapper;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.repository.CompilationsRepository;
import ru.practicum.ewm.repository.EventRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CompilationsAdminServiceImpl implements CompilationsAdminService {
    private final EventRepository eventRepository;
    private final CompilationsRepository compilationsRepository;

    @Override
    public CompilationDto addCompilation(CompilationNewDto compilationDto) {
        Set<Event> events = eventRepository.findByIdIn(compilationDto.getEvents()).orElse(Set.of());
        Compilation compilation = CompilationMapper.toEntityNew(compilationDto);
        compilation.setCompilationEvents(events);
        compilationsRepository.save(compilation);
        log.info("[POST] addCompilation id:{}", compilation.getId());
        return CompilationMapper.toDto(compilation);
    }

    @Override
    public CompilationDto updateCompilation(Long compId, CompilationUpdateDto dto) {
        Compilation compilations = compilationsRepository.findById(compId)
                .orElseThrow(() -> new NullObjectException("Category with id=" + compId + " was not found"));
        Compilation compilationUpdate = CompilationMapper.toEntityUpdate(dto, compilations);

        Set<Long> eventIds = compilations.getCompilationEvents().stream()//1
                .map(Event::getId)
                .collect(Collectors.toSet()
                );

        // Отбираем только новые события
        dto.getEvents().removeAll(eventIds);

        Set<Event> event = eventRepository.findByIdIn(dto.getEvents()).orElse(Set.of());
        event.addAll(compilations.getCompilationEvents());
        compilationUpdate.setCompilationEvents(event);

        if (!event.isEmpty()) {
            compilationsRepository.save(compilationUpdate);
            log.info("[UPDATE] updateCompilation id:{}", compId);
        }

        return CompilationMapper.toDto(compilationUpdate);
    }

    @Override
    public void deleteCompilation(Long compId) {
        compilationsRepository.findById(compId)
                .orElseThrow(() -> new NullObjectException("Category with id=" + compId + " was not found"));

        compilationsRepository.deleteById(compId);
        log.info("[DELETE] deleteCompilation id:{}", compId);
    }
}
