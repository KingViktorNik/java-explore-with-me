package ru.practicum.ewm.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.UserDto;
import ru.practicum.ewm.admin.service.user.UserAdminService;
import ru.practicum.ewm.exception.ConflictException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequestMapping(path = "/admin/users",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserAdminController {
    private final UserAdminService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        UserDto result = service.addUser(userDto);

        return ResponseEntity.status(201).body(result);

    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(name = "ids") Set<Long> ids,
                                  @RequestParam(name = "from", defaultValue = "0") @Min(0) Integer from,
                                  @RequestParam(name = "size", defaultValue = "10") @Min(1) Integer size) {
        if (ids == null) {
            throw new ConflictException("parameter ids = null");
        }

        return service.getUsers(ids, from, size);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@Valid @PathVariable Long userId) {
        service.deleteUser(userId);

        return ResponseEntity.status(204).build();

    }

}
