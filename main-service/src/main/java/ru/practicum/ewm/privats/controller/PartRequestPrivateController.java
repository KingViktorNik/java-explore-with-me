package ru.practicum.ewm.privats.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.partRequest.PartRequestDto;
import ru.practicum.ewm.exception.ValidationException;
import ru.practicum.ewm.privats.service.participation.PartRequestPrivateService;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping(path = "/users/{userId}/requests",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PartRequestPrivateController {
    private final PartRequestPrivateService service;

    @PostMapping
    public ResponseEntity<PartRequestDto> addParticipationRequest(@PathVariable String userId,
                                                                  @RequestParam(value = "eventId", required = false) Long eventId) {
        if (userId == null || eventId == null) {
            throw new ValidationException("Incorrectly made request.");
        }

        Long id = Optional.of(Long.parseLong(userId))
                          .orElseThrow(() -> new ValidationException("Incorrectly made request."));

        PartRequestDto result = service.addParticipationRequest(id, eventId);

        return ResponseEntity.status(201).body(result);

    }

    @PatchMapping("/{requestId}/cancel")
    public ResponseEntity<PartRequestDto> canselParticipationRequest(@PathVariable("userId") Long userId,
                                                                     @PathVariable("requestId") Long requestId) {
        PartRequestDto result = service.canselParticipationRequest(userId, requestId);

        return ResponseEntity.status(200).body(result);

    }

    @GetMapping
    public List<PartRequestDto> getParticipationRequests(@PathVariable Long userId) {
        return service.getParticipationRequests(userId);

    }

}
