package ru.practicum.ewm.service.users.service;

import ru.practicum.ewm.service.users.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto addUser(UserDto userDto);

    List<UserDto> getUsers(Set<Long> userIds, Integer from, Integer size);

    void deleteUser(Long userId);
}
