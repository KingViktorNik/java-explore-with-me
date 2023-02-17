package ru.practicum.ewm.mapper.rating;

import ru.practicum.ewm.dto.RatingUserDto;
import ru.practicum.ewm.model.rating.RatingUser;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RatingUserMapper {

    public static RatingUserDto toDtoLike(RatingUser ratingUser) {
        return new RatingUserDto(ratingUser.getId(),
                                 ratingUser.getName(),
                                 ratingUser.getLike().intValue(),
                                 0,
                                 0F);
    }

    public static RatingUserDto toDtoDislike(RatingUser ratingUser) {
        return new RatingUserDto(ratingUser.getId(),
                                 ratingUser.getName(),
                                 0,
                                 ratingUser.getLike().intValue(),
                                 0F);
    }

    public static List<RatingUserDto> toDtoLikes(List<RatingUser> ratingUsers) {
        return ratingUsers.stream()
                          .map(RatingUserMapper::toDtoLike)
                          .collect(toList());
    }

    public static List<RatingUserDto> toDtoDislikes(List<RatingUser> ratingUsers) {
        return ratingUsers.stream()
                          .map(RatingUserMapper::toDtoDislike)
                          .collect(toList());
    }

}
