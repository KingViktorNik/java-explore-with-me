package ru.practicum.ewm.admin.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.UserDto;
import ru.practicum.ewm.mapper.UserMapper;
import ru.practicum.ewm.repository.UserRepository;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.model.User;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {
    private final UserRepository repository;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        User result = repository.save(user);
        log.info("addUser id:{}", result.getId());

        return UserMapper.toDto(result);

    }

    @Override
    public List<UserDto> getUsers(Set<Long> userIds, Integer from, Integer size) {
        return repository.findByIdIn(userIds,
                                     PageRequest.of(from, size))
                         .stream()
                         .map(UserMapper::toDto)
                         .collect(toList());

    }

    @Override
    public void deleteUser(Long userId) {
        repository.findById(userId)
                  .orElseThrow(() -> new NotFoundException("User with id=" + userId + " was not found"));
        repository.deleteById(userId);
        log.info("deleteUser id:{}", userId);
    }

}
