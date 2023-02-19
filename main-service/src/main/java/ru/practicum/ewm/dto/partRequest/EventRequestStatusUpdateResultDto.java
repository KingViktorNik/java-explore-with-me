package ru.practicum.ewm.dto.partRequest;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class EventRequestStatusUpdateResultDto {
    /** список заявок на участие в событии (подтвержденные) **/
    private final List<PartRequestDto> confirmedRequests;

    /** список заявок на участие в событии (отклоненные) **/
    private final List<PartRequestDto> rejectedRequests;
}
