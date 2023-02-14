package ru.practicum.ewm.publics.service.compilations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.exception.NullObjectException;
import ru.practicum.ewm.mapper.CompilationMapper;
import ru.practicum.ewm.model.Compilation;
import ru.practicum.ewm.repository.CompilationsRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class CompilationsPublicServiceImpl implements CompilationsPublicService {
    private final CompilationsRepository repository;

    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size) {
        List<Compilation> compilations = repository.findByPinned(pinned, PageRequest.of(from, size)).orElse(List.of());
        log.info("[GET] getCompilations size:{}", compilations.size());
        return compilations
                .stream()
                .map(CompilationMapper::toDto)
                .collect(toList());
    }

    @Override
    public CompilationDto getCompilation(Long compId) {
        Compilation compilation = repository.findById(compId)
                .orElseThrow(() -> new NullObjectException("Compilation with id=" + compId + " was not found"));
        log.info("[GET] getCompilation id:{}", compilation.getId());
        return CompilationMapper.toDto(compilation);
    }
}
