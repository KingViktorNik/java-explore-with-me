package ru.practicum.ewm.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RatingUserDto {
    /** id пользователя */
    private Long id;

    /** Имя пользователя **/
    private String name;

    /** Количество like */
    private int like;

    /** Количество dislike */
    private int dislike;

    /** Рейтинг */
    private Float rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingUserDto)) return false;
        return id != null && id.equals(((RatingUserDto) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
