package ru.practicum.ewm.publics.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.dto.event.EventShortInquiryDto;
import ru.practicum.ewm.dto.event.EventShortInquiryDto.*;
import ru.practicum.ewm.exception.ConflictException;
import ru.practicum.ewm.publics.service.event.EventPublicService;
import ru.practicum.ewm.util.DateTimeConverter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(path = "/events",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class EventPublicController {
    private final EventPublicService service;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "text", required = false) String text,
                                         @RequestParam(name = "categories", required = false) Set<Long> categories,
                                         @RequestParam(name = "paid", required = false) Boolean paid,
                                         @RequestParam(name = "rangeStart", required = false) String rangeStart,
                                         @RequestParam(name = "rangeEnd", required = false) String rangeEnd,
                                         @RequestParam(name = "onlyAvailable", required = false, defaultValue = "false") Boolean onlyAvailable,
                                         @RequestParam(name = "sort", required = false, defaultValue = "VIEWS") String viewsSt,
                                         @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                         @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) Integer size) {

        // Проверка валидации значения сортировки и парсинг в ENUM
        EventSort views = viewsSt == null || viewsSt.isEmpty() ? null : EventSort.from(viewsSt)
                .orElseThrow(() -> new ConflictException("sort '" + viewsSt + "' does not exist"));

        // Проверка валидности дат и парсинг в LocalDateTime
        Range range = new Range(
                rangeStart == null ? null : DateTimeConverter.toDateTime(rangeStart),
                rangeEnd == null ? null : DateTimeConverter.toDateTime(rangeEnd)
        );

        Page page = new Page(from, size);
        EventShortInquiryDto dto = new EventShortInquiryDto(text, categories, paid, range, onlyAvailable, views, page);

        return service.getEvents(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventFullDto> getEvent(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(service.getEvent(id));
    }
}
