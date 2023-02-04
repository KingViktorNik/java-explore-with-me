package ru.practicum.ewm.service.users.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.service.users.model.User;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByIdIn(Set<Long> users, Pageable pageable);
}
