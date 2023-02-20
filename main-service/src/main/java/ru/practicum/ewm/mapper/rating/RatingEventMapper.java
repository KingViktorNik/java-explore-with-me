package ru.practicum.ewm.mapper.rating;

import ru.practicum.ewm.dto.rating.RatingEventDto;
import ru.practicum.ewm.model.rating.RatingEvent;
import ru.practicum.ewm.dto.rating.RatingEventDto.Initiator;

import java.util.List;
import java.util.stream.Collectors;

public class RatingEventMapper {

    public static RatingEventDto toDtoLike(RatingEvent ratingEvent) {
        Initiator initiator = new Initiator(ratingEvent.getUserId(),
                                            ratingEvent.getUserName());

        return new RatingEventDto(ratingEvent.getEventId(),
                                  ratingEvent.getTitle(),
                                  ratingEvent.getEventLike().intValue(),
                                  0,
                                  0F,
                                  initiator);

    }

    public static RatingEventDto toDtoDislike(RatingEvent ratingEvent) {
        Initiator initiator = new Initiator(ratingEvent.getUserId(),
                ratingEvent.getUserName());

        return new RatingEventDto(ratingEvent.getEventId(),
                                  ratingEvent.getTitle(),
                                  0,
                                  ratingEvent.getEventLike().intValue(),
                                  0F,
                                  initiator);

    }

    public static List<RatingEventDto> toDtoLikes(List<RatingEvent> likes) {

        return likes.stream()
                    .map(RatingEventMapper::toDtoLike)
                    .collect(Collectors.toList());

    }

    public static List<RatingEventDto> toDtoDislikes(List<RatingEvent> dislike) {

        return dislike.stream()
                      .map(RatingEventMapper::toDtoDislike)
                      .collect(Collectors.toList());

    }

}
