package ru.practicum.ewm.publics.service.compilations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.mapper.CompilationMapper;
import ru.practicum.ewm.model.Compilation;
import ru.practicum.ewm.repository.CompilationsRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CompilationsPublicServiceImpl implements CompilationsPublicService {
    private final CompilationsRepository repository;

    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size) {
        List<Compilation> compilations = repository.findByPinned(pinned,
                                                                 PageRequest.of(from, size))
                                                                            .orElse(List.of());

        return compilations.stream()
                           .map(CompilationMapper::toDto)
                           .collect(toList());

    }

    @Override
    public CompilationDto getCompilation(Long compId) {
        Compilation compilation = repository.findById(compId)
                                            .orElseThrow(() -> new NotFoundException("Compilation with id=" + compId + " was not found"));

        return CompilationMapper.toDto(compilation);

    }

}
