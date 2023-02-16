package ru.practicum.ewm.privats.service.participation;

import ru.practicum.ewm.dto.partRequest.PartRequestDto;

import java.util.List;

public interface PartRequestPrivateService {
    /** Добавление запроса от текущего пользователя на участие в событии
     *
     * @param userId идентификатор пользователя
     * @param eventId идентификатор события
     * @return PartRequestDto
     * **/
    PartRequestDto addParticipationRequest(Long userId, Long eventId);

    /** Отмена своего запроса на участие в событии
     *
     * @param userId идентификатор пользователя
     * @param eventId идентификатор события
     * @return PartRequestDto
     * **/
    PartRequestDto canselParticipationRequest(Long userId, Long eventId);

    /** Получение информации о заявках текущего пользователя на участие в чужих событиях
     *
     * @param userId идентификатор пользователя
     * @return List < PartRequestDto >
     * **/
    List<PartRequestDto> getParticipationRequests(Long userId);
}

