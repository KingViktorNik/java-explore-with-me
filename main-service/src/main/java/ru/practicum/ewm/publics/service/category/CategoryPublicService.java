package ru.practicum.ewm.publics.service.category;

import ru.practicum.ewm.dto.CategoryDto;

import java.util.List;

public interface CategoryPublicService {
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
