package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.model.partRequest.PartRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PartRequestRepository extends JpaRepository<PartRequest, Long> {
     Optional<PartRequest> findByRequesterIdAndId(Long userId, Long requestId);
     Optional<PartRequest> findByEventId(Long event);
     List<PartRequest> getByEventIdAndIdIn(Long eventId, Set<Long> requestId);
     List<PartRequest> getByEventId(Long eventId);
     List<PartRequest> getByRequesterId(Long userId);
}
