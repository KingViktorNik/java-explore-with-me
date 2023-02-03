package ru.practicum.ewm.server.stats.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.stats.dto.EndpointHitDto;
import ru.practicum.ewm.dto.stats.dto.StatsAnswerDto;
import ru.practicum.ewm.server.stats.service.StatsService;

import java.util.List;
import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class StatsController {
    private StatsService service;

    @PostMapping(path = "/hit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveHit(@Valid @RequestBody EndpointHitDto endpointHitDto) {
        endpointHitDto = service.saveHit(endpointHitDto);
        log.info("[POST] saveHit id:{}", endpointHitDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/stats")
    public List<StatsAnswerDto> getStats(@RequestParam(name = "start") String start,
                                         @RequestParam(name = "end") String end,
                                         @RequestParam(name = "uris") List<String> uris,
                                         @RequestParam(name = "unique", defaultValue = "false") Boolean unique) {
        List<StatsAnswerDto> answerDtos = service.getStats(start, end, uris, unique);
        log.info("[GET] getStats listSize:{}", answerDtos.size());
        return answerDtos;
    }

}