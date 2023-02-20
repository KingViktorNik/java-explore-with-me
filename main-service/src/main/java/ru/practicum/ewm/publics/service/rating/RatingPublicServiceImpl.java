package ru.practicum.ewm.publics.service.rating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.rating.RatingEventDto;
import ru.practicum.ewm.dto.rating.RatingUserDto;
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

        List<RatingEventDto> ratingLikeDtos = RatingEventMapper.toDtoLikes(eventLike);
        List<RatingEventDto> ratingDislikeDtos = RatingEventMapper.toDtoDislikes(eventDislike);

        List<RatingEventDto> result = new ArrayList<>();

        // объединение списков лаков и дизлайков для расчета общего рейтинга события
        for (RatingEventDto ratingEvent: ratingLikeDtos) {
            int indexDislike = ratingDislikeDtos.indexOf(ratingEvent);

            // если у события есть дизлайки
            if (indexDislike > -1) {
                int dislike = ratingDislikeDtos.get(indexDislike).getDislike();

                // добавление дизлайк в общий рейтинг
                ratingEvent.setDislike(dislike);

                // удаление дизлайка из списка дизлайков
                ratingDislikeDtos.remove(indexDislike);
            }

            // вычисление рейтинг
            float rating = rating(ratingEvent.getLike(), ratingEvent.getDislike());

            // добавление среднего значения рейтинга
            ratingEvent.setRating(rating);

            // сохранение результата в ДТО
            result.add(ratingEvent);
        }

        // добавляем события у которых нет лайков
        result.addAll(ratingDislikeDtos);

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
