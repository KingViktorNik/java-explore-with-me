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

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static ru.practicum.ewm.dto.event.EventShortInquiryDto.EventSort.VIEWS;


@RestController
@RequestMapping(path = "/events",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class EventPublicController {
    private final EventPublicService service;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "text",           required = false) String text,
                                         @RequestParam(name = "categories",     required = false) Set<Long> categories,
                                         @RequestParam(name = "paid",           required = false) Boolean paid,
                                         @RequestParam(name = "rangeStart",     required = false) String rangeStartSt,
                                         @RequestParam(name = "rangeEnd",       required = false) String rangeEndSt,
                                         @RequestParam(name = "onlyAvailable",  required = false, defaultValue = "false") Boolean onlyAvailable,
                                         @RequestParam(name = "sort",           required = false) String sortSt,
                                         @RequestParam(name = "from",           required = false, defaultValue = "0") @Min(0) Integer from,
                                         @RequestParam(name = "size",           required = false, defaultValue = "10") @Min(1) Integer size,
                                         HttpServletRequest request) {

        // Проверка валидации значения сортировки и парсинг в ENUM
        EventSort sort = VIEWS;
        if (sortSt != null && !sortSt.isEmpty()) {
            sort = EventSort.from(sortSt)
                            .orElseThrow(() -> new ConflictException("sort '" + sortSt + "' does not exist"));
        }

        LocalDateTime rangeStart = null;
        if (rangeStartSt != null) {
            rangeStart = DateTimeConverter.toDateTime(rangeStartSt);
        }

        LocalDateTime rangeEnd = null;
        if (rangeEndSt != null) {
            rangeEnd = DateTimeConverter.toDateTime(rangeStartSt);
        }


        EventShortInquiryDto dto = EventShortInquiryDto.builder()
                                                       .text(text)
                                                       .categories(categories)
                                                       .paid(paid)
                                                       .start(rangeStart)
                                                       .end(rangeEnd)
                                                       .onlyAvailable(onlyAvailable)
                                                       .sort(sort)
                                                       .from(from)
                                                       .size(size)
                                                       .build();

        return service.getEvents(dto, request);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EventFullDto> getEvent(@PathVariable Long id, HttpServletRequest request) {
        EventFullDto result = service.getEvent(id, request);

        return ResponseEntity.status(200).body(result);

    }

}
