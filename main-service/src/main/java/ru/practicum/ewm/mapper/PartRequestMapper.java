package ru.practicum.ewm.mapper;

import ru.practicum.ewm.dto.partRequest.PartRequestDto;
import ru.practicum.ewm.model.part_request.PartRequest;
import ru.practicum.ewm.util.DateTimeConverter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PartRequestMapper {
    public static PartRequestDto toDto(PartRequest entity) {
        String created = DateTimeConverter.toString(entity.getCreated());

        return PartRequestDto.builder()
                             .id(entity.getId())
                             .created(created)
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
