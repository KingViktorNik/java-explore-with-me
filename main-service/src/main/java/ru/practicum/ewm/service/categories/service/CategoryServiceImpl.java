package ru.practicum.ewm.service.categories.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.categories.dto.CategoryDto;
import ru.practicum.ewm.service.categories.mapper.CategoryMapper;
import ru.practicum.ewm.service.categories.model.Category;
import ru.practicum.ewm.service.categories.repository.CategoryRepository;
import ru.practicum.ewm.service.exception.NullObjectException;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = repository.save(CategoryMapper.toEntity(categoryDto));
        log.info("[POST] addCategory id:{}", category.getId());
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long catId) {
        repository.findById(catId)
                .orElseThrow(() -> new NullObjectException("Category with id=" + catId + " was not found"));

        categoryDto.setId(catId);
        Category category = repository.save(CategoryMapper.toEntity(categoryDto));

        return CategoryMapper.toDto(category);
    }

    @Override
    public void deleteCategory(Long catId) {
        repository.findById(catId)
                .orElseThrow(() -> new NullObjectException("Category with id=" + catId + " was not found"));
        repository.deleteById(catId);
    }

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
