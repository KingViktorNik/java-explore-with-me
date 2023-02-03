package ru.practicum.ewm.server.stats.mapper;

import ru.practicum.ewm.dto.stats.dto.EndpointHitDto;
import ru.practicum.ewm.dto.stats.util.DateTimeConverter;
import ru.practicum.ewm.server.stats.model.EndpointHit;

public class EndpointHitMapper {
    public static EndpointHit toEntity(EndpointHitDto dto) {
        return new EndpointHit(
                dto.getId(),
                dto.getApp(),
                dto.getUri(),
                dto.getIp(),
                DateTimeConverter.toDateTime(dto.getTimestamp())
        );
    }

    public static EndpointHitDto toDto(EndpointHit entity) {
        return new EndpointHitDto(
                entity.getId(),
                entity.getApp(),
                entity.getUri(),
                entity.getIp(),
                DateTimeConverter.toString(entity.getTimestamp())
        );
    }
}
