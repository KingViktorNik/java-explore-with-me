package ru.practicum.ewm.service.users.mapper;

import ru.practicum.ewm.service.users.dto.UserDto;
import ru.practicum.ewm.service.users.model.User;

public class UserMapper {

    public static User toEntity(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getName()
        );
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}
