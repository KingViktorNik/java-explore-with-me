package ru.practicum.ewm.dto.partRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@AllArgsConstructor
public class EventRequestStatusUpdateRequestDto {
    /** Идентификаторы запросов на участие в событии текущего пользователя **/
    @NotNull
    private final Set<Long> requestIds;

    /** Новый статус запроса на участие в событии текущего пользователя **/
    @NotNull
    private final String status;
}
