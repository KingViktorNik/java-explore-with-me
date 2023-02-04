package ru.practicum.ewm.service.categories.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.categories.dto.CategoryDto;
import ru.practicum.ewm.service.categories.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin/categories",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@AllArgsConstructor
public class CategoryAdminController {
    private final CategoryService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(service.addCategory(categoryDto));
    }

    @PatchMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable Long catId,
                                                      @Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(service.updateCategory(categoryDto, catId));
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long catId) {
        service.deleteCategory(catId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
