package ru.practicum.ewm.publics.service.rating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.RatingEventDto;
import ru.practicum.ewm.dto.RatingUserDto;
import ru.practicum.ewm.mapper.rating.RatingEventMapper;
import ru.practicum.ewm.mapper.rating.RatingUserMapper;
import ru.practicum.ewm.model.rating.RatingEvent;
import ru.practicum.ewm.model.rating.RatingUser;
import ru.practicum.ewm.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class RatingPublicServiceImpl implements RatingPublicService {
    private final RatingRepository repository;

    @Override
    public List<RatingEventDto> getRatingEvents() {
        // список положительных отзывов событий
        List<RatingEvent> eventLike = repository.findAllRatingEvents(true);
        // список отрицательных отзывов событий
        List<RatingEvent> eventDislike = repository.findAllRatingEvents(false);

        List<RatingEventDto> ratingEventLikeDtos = RatingEventMapper.toDtoLikes(eventLike);
        List<RatingEventDto> ratingEventDislikeDtos = RatingEventMapper.toDtoDislikes(eventDislike);

        List<RatingEventDto> result = new ArrayList<>();

        // объединяем списки
        for (RatingEventDto like: ratingEventLikeDtos) {
            int indexDislike = ratingEventDislikeDtos.indexOf(like);
            // если у события есть дизлайки
            if (indexDislike > -1) {
                like.setDislike(ratingEventDislikeDtos.get(indexDislike).getDislike());
                // удаляем из списка дизлайков
                ratingEventDislikeDtos.remove(indexDislike);
            }
            // вычисляем рейтинг
            like.setRating(rating(like.getLike(), like.getDislike()));
            result.add(like);
        }

        // добавляем события у которых нет лайков
        result.addAll(ratingEventDislikeDtos);

        return result.stream()
                     .sorted((o1, o2) -> o2.getRating().compareTo(o1.getRating()))
                     .collect(toList());

    }

    @Override
    public List<RatingUserDto> getRatingInitiator() {

        List<RatingUser> usersLike = repository.findAllRatingUsers(true);
        List<RatingUser> usersDislike = repository.findAllRatingUsers(false);

        List<RatingUserDto> ratingUserLikeDtos = RatingUserMapper.toDtoLikes(usersLike);
        List<RatingUserDto> ratingUserDislikeDtos = RatingUserMapper.toDtoDislikes(usersDislike);

        List<RatingUserDto> result = new ArrayList<>();

        // объединяем списки
        for (RatingUserDto like: ratingUserLikeDtos) {
            int indexDislike = ratingUserDislikeDtos.indexOf(like);
            // если у события есть дизлайки
            if (indexDislike > -1) {
                like.setDislike(ratingUserDislikeDtos.get(indexDislike).getDislike());
                // удаляем из списка дизлайков
                ratingUserDislikeDtos.remove(indexDislike);
            }
            // вычисляем рейтинг
            like.setRating(rating(like.getLike(), like.getDislike()));
            result.add(like);
        }

        // добавляем события у которых нет лайков
        result.addAll(ratingUserDislikeDtos);

        return result.stream()
                .sorted((o1, o2) -> o2.getRating().compareTo(o1.getRating()))
                .collect(toList());

    }

    private static float rating(int like, int dislike) {
        // шакала рейтинга: 0 - 10
        return ((float) like / (like + dislike)) * 10;
    }

}
