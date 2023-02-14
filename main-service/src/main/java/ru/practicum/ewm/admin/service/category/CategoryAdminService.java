package ru.practicum.ewm.admin.service.category;

import ru.practicum.ewm.dto.CategoryDto;

public interface CategoryAdminService {
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
     * @param catId       идентификатор категории
     * @return CategoryDto
     */
    CategoryDto updateCategory(CategoryDto categoryDto, Long catId);

    /**
     * Удаление категории
     *
     * @param catId идентификатор категории
     */
    void deleteCategory(Long catId);
}
