package ru.practicum.ewm.admin.service.user;

import ru.practicum.ewm.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserAdminService {
    UserDto addUser(UserDto userDto);

    List<UserDto> getUsers(Set<Long> userIds, Integer from, Integer size);

    void deleteUser(Long userId);
}
