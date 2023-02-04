package ru.practicum.ewm.service.categories.service;

import ru.practicum.ewm.service.categories.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    /**
     * Добавление новый категории
     *
     * @param categoryDto
     * @return
     */
    CategoryDto addCategory(CategoryDto categoryDto);

    /**
     * Изменение категории
     *
     * @param categoryDto
     * @param catId идентификатор категории
     * @return CategoryDto
     */
    CategoryDto updateCategory(CategoryDto categoryDto, Long catId);

    /**
     * Удаление категории
     *
     * @param catId идентификатор категории
     */
    void deleteCategory(Long catId);

    /**
     * Получение категорий
     *
     * @return List<CategoryDto>
     */
    List<CategoryDto> getCategories(Integer from, Integer size);

    /**
     * Получение информации о категории по её идентификатору
     *
     * @param catId идентификатор категории
     * @return CategoryDto
     */
    CategoryDto getCategory(Long catId);
}
