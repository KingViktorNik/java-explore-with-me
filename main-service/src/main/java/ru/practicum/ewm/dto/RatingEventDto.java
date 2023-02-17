package ru.practicum.ewm.dto;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RatingEventDto {
    /** id события */
    private Long id;

    /** Краткое описание события **/
    private String title;

    /** Количество like */
    private int like;

    /** Количество dislike */
    private int dislike;

    /** Рейтинг */
    private Float rating;

    /** Инициатор **/
    private Initiator initiator;

    @Getter
    @Setter
    @AllArgsConstructor
    public static final class Initiator {
        /** id инициатора события */
        private long id;

        /** Имя инициатора события*/
        private String name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingEventDto)) return false;
        return id != null && id.equals(((RatingEventDto) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
