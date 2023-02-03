package ru.practicum.ewm.service;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.client.stats.StatsClient;
import ru.practicum.ewm.dto.stats.dto.EndpointHitDto;

import java.util.List;

@Validated
@RestController
@RequestMapping(
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class TestController {

    @GetMapping("/test/hit")
    public Object testHit() {
        return StatsClient.saveHit(new EndpointHitDto(null, "ewm-main-service", "/events/1", "192.163.0.1", "2022-09-06 11:00:23"));
    }

    @GetMapping("/test/stats/unique")
    public Object testStatsUnique() {
        return StatsClient.getStats("2022-09-06 11:00:23", "2022-09-06 22:00:23", List.of("/events/2", "/events/1", "/events/3"), true);
    }

    @GetMapping("/test/stats")
    public Object testStats() {
        return StatsClient.getStats("2022-09-06 11:00:23", "2022-09-06 22:00:23", List.of("/events/2", "/events/1", "/events/3"), false);
    }
}
