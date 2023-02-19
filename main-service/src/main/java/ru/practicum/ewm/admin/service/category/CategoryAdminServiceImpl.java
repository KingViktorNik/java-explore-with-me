package ru.practicum.ewm.admin.service.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.repository.CategoryRepository;
import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.mapper.CategoryMapper;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.exception.NotFoundException;

import javax.validation.ValidationException;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryAdminServiceImpl implements CategoryAdminService {
    private final CategoryRepository repository;

    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        Category category = CategoryMapper.toEntity(dto);
        Category result = repository.save(category);
        log.info("addCategory id:{}", result.getId());

        return CategoryMapper.toDto(result);

    }

    @Override
    public CategoryDto updateCategory(Long catId, CategoryDto categoryDto) {
        repository.findById(catId)
                  .orElseThrow(() -> new NotFoundException("Category with id=" + catId + " was not found"));
        categoryDto.setId(catId);
        Category result = repository.save(CategoryMapper.toEntity(categoryDto));
        log.info("updateCategory id:{}", result.getId());

        return CategoryMapper.toDto(result);

    }

    @Override
    public void deleteCategory(Long catId) {
        repository.findById(catId)
                  .orElseThrow(() -> new ValidationException("Category with id=" + catId + " was not found"));
        repository.deleteById(catId);

    }

}
