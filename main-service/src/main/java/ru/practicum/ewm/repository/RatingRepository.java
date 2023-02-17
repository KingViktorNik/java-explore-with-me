package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.model.rating.Rating;
import ru.practicum.ewm.repository.custom.CustomRatingRepository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>, CustomRatingRepository {
    /** Запрос по id Пользователя и id События **/
    Optional<Rating> findByUserIdAndEventId(Long userId, Long eventId);

}
