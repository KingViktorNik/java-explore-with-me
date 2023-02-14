package ru.practicum.ewm.privats.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.partRequest.EventRequestStatusUpdateRequestDto;
import ru.practicum.ewm.dto.partRequest.EventRequestStatusUpdateResultDto;
import ru.practicum.ewm.dto.partRequest.PartRequestDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventNewDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;
import ru.practicum.ewm.privats.service.event.EventPrivateService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/events",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class EventPrivateController {
    private final EventPrivateService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventFullDto> addEvent(@PathVariable Long userId,
                                                 @Valid @RequestBody EventNewDto eventNewDto) {
        return ResponseEntity.status(201).body(service.addEvent(userId, eventNewDto));
    }

    @PatchMapping(path = "/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventFullDto> updateEvent(@PathVariable Long userId,
                                                    @PathVariable Long eventId,
                                                    @Valid @RequestBody EventUpdateDto eventUpdateDto) {
        return ResponseEntity.status(200).body(service.updateEvent(userId, eventId, eventUpdateDto));
    }


    @GetMapping
    public List<EventShortDto> getEvents(@PathVariable Long userId,
                                         @RequestParam(name = "from", defaultValue = "0") @Min(0) Integer from,
                                         @RequestParam(name = "size", defaultValue = "10") @Min(1) Integer size) {
        return service.getEvents(userId, from, size);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventFullDto> getEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        return ResponseEntity.ok(service.getEvent(userId, eventId));
    }

    @PatchMapping(value = "/{eventId}/requests", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventRequestStatusUpdateResultDto> updateRequests(@PathVariable Long userId,
                                                                            @PathVariable Long eventId,
                                                                            @RequestBody @Valid EventRequestStatusUpdateRequestDto dto) {
        return ResponseEntity.ok(service.updateRequests(userId, eventId, dto));
    }

    @GetMapping("/{eventId}/requests")
    public List<PartRequestDto> getRequests(@PathVariable Long userId, @PathVariable Long eventId) {
        return service.getRequests(userId, eventId);
    }

}
