package ru.practicum.ewm.admin.service.category;

import ru.practicum.ewm.dto.CategoryDto;

public interface CategoryAdminService {
    /**
     * Добавление новый категории
     *
     * @param categoryDto новая категория
     * @return CategoryDto
     */
    CategoryDto addCategory(CategoryDto categoryDto);

    /**
     * Изменение категории
     *
     * @param catId       идентификатор категории
     * @param categoryDto новая категория
     * @return CategoryDto
     */
    CategoryDto updateCategory(Long catId, CategoryDto categoryDto);

    /**
     * Удаление категории
     *
     * @param catId идентификатор категории
     */
    void deleteCategory(Long catId);

}
