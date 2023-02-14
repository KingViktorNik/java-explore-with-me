package ru.practicum.ewm.publics.service.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.repository.CategoryRepository;
import ru.practicum.ewm.exception.NullObjectException;
import ru.practicum.ewm.mapper.CategoryMapper;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryPublicServiceImpl implements CategoryPublicService {
    private final CategoryRepository repository;

    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        return repository.findAll(PageRequest.of(from, size)).stream()
                .map(CategoryMapper::toDto)
                .sorted(Comparator.comparing(CategoryDto::getName))
                .collect(toList());
    }

    @Override
    public CategoryDto getCategory(Long catId) {
        return CategoryMapper.toDto(repository.findById(catId)
                .orElseThrow(() -> new NullObjectException("Category with id=" + catId + " was not found"))
        );
    }
}
