package ru.practicum.ewm.server.stats.mapper;

import ru.practicum.ewm.dto.stats.dto.EndpointHitDto;
import ru.practicum.ewm.dto.stats.util.DateTimeConverter;
import ru.practicum.ewm.server.stats.model.EndpointHit;

import java.time.LocalDateTime;

public class EndpointHitMapper {
    public static EndpointHit toEntity(EndpointHitDto dto) {
        LocalDateTime dateTime = null;
        if (dto.getTimestamp() != null) {
            dateTime = DateTimeConverter.toDateTime(dto.getTimestamp());
        }

        return new EndpointHit(dto.getId(),
                               dto.getApp(),
                               dto.getUri(),
                               dto.getIp(),
                               dateTime);

    }

    public static EndpointHitDto toDto(EndpointHit entity) {
        String dateTime = null;
        if (entity.getTimestamp() != null) {
            dateTime = DateTimeConverter.toString(entity.getTimestamp());
        }

        return new EndpointHitDto(entity.getId(),
                                  entity.getApp(),
                                  entity.getUri(),
                                  entity.getIp(),
                                  dateTime);

    }

}
