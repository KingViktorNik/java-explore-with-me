package ru.practicum.ewm.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.ewm.model.rating.RatingEvent;
import ru.practicum.ewm.model.rating.RatingUser;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class CustomRatingRepositoryImpl implements CustomRatingRepository {
    @Autowired
    private EntityManager em;

    public List<RatingUser> findAllRatingUsers(Boolean likeEvent) {
        String sql = "select new ru.practicum.ewm.model.rating.RatingUser(e.initiator.id, u.name, count(e.initiator.id)) " +
                     "from Rating r " +
                     "left join Event e on r.event.id = e.id " +
                     "left join User u on u.id = e.initiator.id " +
                     "where r.likeEvent = :likeEvent " +
                     "group by e.initiator.id, u.name";

        TypedQuery<RatingUser> query = em.createQuery(sql, RatingUser.class);
        query.setParameter("likeEvent", likeEvent);

        return query.getResultList();

    }

    public List<RatingEvent> findAllRatingEvents(Boolean likeEvent) {
        String sql = "select new ru.practicum.ewm.model.rating.RatingEvent(r.event.id, e.title, count(r.event.id), u.id, u.name) " +
                     "from Rating r " +
                     "left join Event e on r.event.id = e.id " +
                     "left join User u on u.id = e.initiator.id " +
                     "where r.likeEvent = :likeEvent " +
                     "group by r.event.id, e.title, u.id, u.name";

        TypedQuery<RatingEvent> query = em.createQuery(sql, RatingEvent.class);
        query.setParameter("likeEvent", likeEvent);

        return query.getResultList();

    }

}
