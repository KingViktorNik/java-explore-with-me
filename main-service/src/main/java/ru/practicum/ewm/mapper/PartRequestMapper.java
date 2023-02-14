package ru.practicum.ewm.mapper;

import ru.practicum.ewm.dto.partRequest.PartRequestDto;
import ru.practicum.ewm.model.partRequest.PartRequest;
import ru.practicum.ewm.util.DateTimeConverter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PartRequestMapper {
    public static PartRequestDto toDto(PartRequest entity) {
        return PartRequestDto.builder()
                .id(entity.getId())
                .created(DateTimeConverter.toString(entity.getCreated()))
                .event(entity.getEventId())
                .requester(entity.getRequesterId())
                .status(entity.getStatus().toString())
                .build();
    }

    public static List<PartRequestDto> toDtos(List<PartRequest> requests) {
        return requests.stream()
                .map(PartRequestMapper::toDto)
                .collect(toList());
    }
}
