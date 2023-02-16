package ru.practicum.ewm.admin.service.user;

import ru.practicum.ewm.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserAdminService {
    /**
     * Добавление нового пользователя
     *
     * @param userDto новый пользователь
     * @return UserDto
     */
    UserDto addUser(UserDto userDto);

    /**
     * Получить информацию о пользователях
     *
     * @param userIds список идентификаторов пользователей
     * @param from    PageRequest страница
     * @param size    PageRequest количество элементов
     * @return List < UserDto >
     */
    List<UserDto> getUsers(Set<Long> userIds, Integer from, Integer size);

    /**
     * Удаление категории
     *
     * @param userId идентификатор пользователя
     */
    void deleteUser(Long userId);

}
