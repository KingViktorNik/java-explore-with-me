package ru.practicum.ewm.dto.partRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EventRequestStatusUpdateResultDto {
    private final List<PartRequestDto> confirmedRequests;
    private final List<PartRequestDto> rejectedRequests;
}
