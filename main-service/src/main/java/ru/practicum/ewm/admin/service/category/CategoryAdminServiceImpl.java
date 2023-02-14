package ru.practicum.ewm.admin.service.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.repository.CategoryRepository;
import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.mapper.CategoryMapper;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.exception.NullObjectException;

import javax.validation.ValidationException;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryAdminServiceImpl implements CategoryAdminService {
    private final CategoryRepository repository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = repository.save(CategoryMapper.toEntity(categoryDto));
        log.info("[POST] addCategory id:{}", category.getId());
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long catId) {
        try {
            repository.findById(catId);
        } catch (RuntimeException e) {
            throw new NullObjectException("Category with id=" + catId + " was not found");
        }

        categoryDto.setId(catId);
        Category category = repository.save(CategoryMapper.toEntity(categoryDto));

        return CategoryMapper.toDto(category);
    }

    @Override
    public void deleteCategory(Long catId) {
        repository.findById(catId)
                .orElseThrow(() -> new ValidationException("Category with id=" + catId + " was not found"));
        repository.deleteById(catId);
    }
}
