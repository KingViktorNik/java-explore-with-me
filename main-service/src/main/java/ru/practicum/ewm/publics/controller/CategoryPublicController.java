package ru.practicum.ewm.publics.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.publics.service.category.CategoryPublicService;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/categories",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@AllArgsConstructor
public class CategoryPublicController {
    private final CategoryPublicService service;

    @GetMapping
    public List<CategoryDto> getCategories(@RequestParam(value = "from", defaultValue = "0") @Min(0) Integer from,
                                           @RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size) {
        return service.getCategories(from, size);

    }

    @GetMapping("/{catId}")
    private ResponseEntity<CategoryDto> getCategory(@PathVariable Long catId) {
        CategoryDto result = service.getCategory(catId);

        return ResponseEntity.status(200).body(result);

    }

}
