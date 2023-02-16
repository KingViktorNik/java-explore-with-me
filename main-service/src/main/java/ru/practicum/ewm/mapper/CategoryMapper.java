package ru.practicum.ewm.mapper;

import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.model.Category;

public class CategoryMapper {
    public static Category toEntity(CategoryDto dto) {

        return new Category(dto.getId(), dto.getName());

    }

    public static CategoryDto toDto(Category entity) {

        return new CategoryDto(entity.getId(), entity.getName());

    }

}
