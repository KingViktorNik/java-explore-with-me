package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.model.Compilation;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompilationsRepository extends JpaRepository<Compilation, Long> {
    Optional<List<Compilation>> findByPinned(Boolean pinned, Pageable pageable);
}
