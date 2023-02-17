package ru.practicum.ewm.publics.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.dto.RatingEventDto;
import ru.practicum.ewm.dto.RatingUserDto;
import ru.practicum.ewm.publics.service.rating.RatingPublicService;

import java.util.List;

@RestController
@RequestMapping(path = "/rating",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RatingPublicController {
    private RatingPublicService service;

    @GetMapping("/events")
    public List<RatingEventDto> getRatingEvents() {

        return service.getRatingEvents();

    }

    @GetMapping("/users")
    public List<RatingUserDto> getRatingInitiator() {

        return service.getRatingInitiator();

    }

}
