package ru.practicum.ewm.model.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RatingEvent {
    /** id события */
    private Long eventId;

    /** Описания события */
    private String title;

    /** Количество лайков */
    private Long eventLike;

    /** id инициатора */
    private Long userId;

    /** имя инициатора **/
    private String userName;

}
