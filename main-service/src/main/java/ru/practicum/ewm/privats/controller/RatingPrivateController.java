package ru.practicum.ewm.privats.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.exception.ValidationException;
import ru.practicum.ewm.privats.service.rating.RatingPrivateService;


@RestController
@RequestMapping(path = "/users/{userId}/events/{eventId}/rating",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class RatingPrivateController {
    private RatingPrivateService service;

    @PatchMapping
    public ResponseEntity<Void> like(@PathVariable Long userId,
                                     @PathVariable Long eventId,
                                     @RequestParam(name = "like", required = false) Boolean isLike) {
        if (userId == null || eventId == null || isLike == null) {
            throw new ValidationException("Ошибка ввода данных");
        }
        service.updateLike(userId, eventId, isLike);

        return ResponseEntity.status(201).build();

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLike(@PathVariable Long userId,
                                           @PathVariable Long eventId) {
        service.deleteLike(userId, eventId);

        return ResponseEntity.status(204).build();

    }

}
