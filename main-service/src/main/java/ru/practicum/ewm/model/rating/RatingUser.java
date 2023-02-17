package ru.practicum.ewm.model.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RatingUser {
    /** id пользователя */
    private Long id;

    /** имя пользователя */
    private String name;

    /** Количество лайков */
    private Long like;

}
