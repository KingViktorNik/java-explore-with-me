package ru.practicum.ewm.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.admin.service.event.EventAdminService;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventInquiryDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;
import ru.practicum.ewm.exception.ConflictException;
import ru.practicum.ewm.model.event.enums.EventState;
import ru.practicum.ewm.util.DateTimeConverter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Validated
@RestController
@RequestMapping(path = "/admin/events",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EventAdminController {
    private final EventAdminService service;

    @GetMapping
    public List<EventFullDto> getEvents(@RequestParam(name = "users", required = false) Set<Long> users,
                                        @RequestParam(name = "states", required = false) List<String> statesSt,
                                        @RequestParam(name = "categories", required = false) Set<Long> categories,
                                        @RequestParam(name = "rangeStart", required = false) String rangeStart,
                                        @RequestParam(name = "rangeEnd", required = false) String rangeEnd,
                                        @RequestParam(name = "from", defaultValue = "0") @Min(0) Integer from,
                                        @RequestParam(name = "size", defaultValue = "10") @Min(1) Integer size) {

        // Проверка валидации статусов и парсинг в ENUM
        Set<EventState> states = statesSt == null || statesSt.isEmpty()
                ? null
                : statesSt.stream()
                .map(s -> EventState.from(s)
                        .orElseThrow(() -> new ConflictException("status '" + s + "' does not exist"))
                )
                .collect(toSet()
                );

        return service.getEvents(new EventInquiryDto(users,
                states, categories,
                rangeStart == null ? null : DateTimeConverter.toDateTime(rangeStart),
                rangeEnd == null ? null : DateTimeConverter.toDateTime(rangeEnd),
                from,
                size
        ));
    }

    @PatchMapping(path = "/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventFullDto> updateEvent(@PathVariable Long eventId,
                                                    @Valid @RequestBody EventUpdateDto updateDto) {
        return ResponseEntity.ok(service.updateEvent(eventId, updateDto));

    }
}
