package ru.practicum.ewm.server.stats.mapper;

import ru.practicum.ewm.dto.stats.dto.StatsAnswerDto;
import ru.practicum.ewm.server.stats.model.StatsQueryView;

public class StatsAnswerMapper {
    public static StatsAnswerDto toDto(StatsQueryView statsQueryView) {
        return new StatsAnswerDto(statsQueryView.getApp(),
                                  statsQueryView.getUri(),
                                  statsQueryView.getHits());

    }

}
