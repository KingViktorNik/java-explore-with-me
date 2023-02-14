package ru.practicum.ewm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.model.User;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByIdIn(Set<Long> users, Pageable pageable);
}
