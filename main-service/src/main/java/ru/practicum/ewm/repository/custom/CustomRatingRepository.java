package ru.practicum.ewm.repository.custom;

import ru.practicum.ewm.model.rating.RatingEvent;
import ru.practicum.ewm.model.rating.RatingUser;

import java.util.List;

public interface CustomRatingRepository {
    /** Запрос на получения рейтинга пользователей
     *
     * @param likeEvent true  положительные отзывы
     *                  false отрицательные отзывы
     * @return List < RatingUser >
     * **/
    List<RatingUser> findAllRatingUsers(Boolean likeEvent);

    /** Запрос на получения рейтинга событий
     *
     * @param likeEvent true  положительные отзывы
     *                  false отрицательные отзывы
     * @return List < RatingUser >
     * **/

    List<RatingEvent> findAllRatingEvents(Boolean likeEvent);

}
