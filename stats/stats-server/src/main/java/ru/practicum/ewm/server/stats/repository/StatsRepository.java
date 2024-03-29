package ru.practicum.ewm.server.stats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.ewm.server.stats.model.EndpointHit;
import ru.practicum.ewm.server.stats.model.StatsQueryView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface StatsRepository extends JpaRepository<EndpointHit, Long> {
    /**
     * Получение статистики по посещениям. Все посещения
     */
    @Query("SELECT e.app as app, e.uri as uri, count(e.uri) as hits " +
            "FROM EndpointHit as e " +
            "WHERE uri IN :uris and e.timestamp >= :start and e.timestamp <= :end " +
            "GROUP BY uri, app ")
    List<StatsQueryView> findByUri(@Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end,
                                   @Param("uris") Set<String> uris);

    /**
     * Получение статистики по посещениям. Только уникальные посещения (unique = true)
     */
    @Query("SELECT e.app as app, e.uri as uri, count(e.uri) as hits " +
            "FROM EndpointHit as e " +
            "WHERE uri IN :uris " +
            "and e.timestamp >= :start " +
            "and e.timestamp <= :end " +
            "and e.id IN(SELECT MIN(e_h.id) " +
                        "FROM EndpointHit as e_h " +
                        "GROUP BY e_h.ip ) " +
            "GROUP BY uri, app ")
    List<StatsQueryView> findByUriUnique(@Param("start") LocalDateTime start,
                                         @Param("end") LocalDateTime end,
                                         @Param("uris") Set<String> uris);
}
