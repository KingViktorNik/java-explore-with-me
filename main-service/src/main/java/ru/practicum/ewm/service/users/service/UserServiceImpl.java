package ru.practicum.ewm.service.users.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.exception.ConflictException;
import ru.practicum.ewm.service.exception.NullObjectException;
import ru.practicum.ewm.service.users.dto.UserDto;
import ru.practicum.ewm.service.users.mapper.UserMapper;
import ru.practicum.ewm.service.users.model.User;
import ru.practicum.ewm.service.users.repository.UserRepository;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user;
        try {
            user = repository.save(UserMapper.toEntity(userDto));
        } catch (Exception e) {
            log.debug("Ошибка записи в базу данных");
            throw new ConflictException("User with mail " + userDto.getEmail() + " already registered");
        }
        log.info("[POST] addUser id:{}", user.getId());
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDto> getUsers(Set<Long> userIds, Integer from, Integer size) {
        return repository.findByIdIn(userIds, PageRequest.of(from, size)).stream()
                .map(UserMapper::toDto)
                .collect(toList());
    }

    @Override
    public void deleteUser(Long userId) {
        repository.findById(userId).orElseThrow(() -> new NullObjectException("User with id=" + userId + " was not found"));
        repository.deleteById(userId);
    }
}
