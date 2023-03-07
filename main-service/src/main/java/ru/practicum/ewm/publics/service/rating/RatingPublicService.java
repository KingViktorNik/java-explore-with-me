package ru.practicum.ewm.publics.service.rating;

import ru.practicum.ewm.dto.rating.RatingEventDto;
import ru.practicum.ewm.dto.rating.RatingUserDto;

import java.util.List;

public interface RatingPublicService {
    /** Рейтинг событий */
    List<RatingEventDto> getRatingEvents();

    /** Рейтинг пользователей (организаторов мероприятий) */
    List<RatingUserDto> getRatingInitiator();

}
