package ru.practicum.ewm.privats.service.rating;

public interface RatingPrivateService {
    /** Оценка мероприятия
     * @param userId идентификатор пользователя(участника события)
     * @param eventId идентификатор события
     * @param like   оценка события (true - положительное)
     * **/
    void updateLike(Long userId, Long eventId, Boolean like);

    /** Удаление оценки мероприятия
     * @param userId идентификатор пользователя(участника события)
     * @param eventId идентификатор события
     * **/
    void deleteLike(Long userId, Long eventId);
}
