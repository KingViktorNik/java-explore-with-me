package ru.practicum.ewm.service.users.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.users.dto.UserDto;
import ru.practicum.ewm.service.users.service.UserService;

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
public class UserController {
    private final UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(service.addUser(userDto));
    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(name = "ids") Set<Long> ids,
                                  @RequestParam(name = "from", defaultValue = "0") @Min(0) Integer from,
                                  @RequestParam(name = "size", defaultValue = "10") @Min(1) Integer size) {
        return service.getUsers(ids, from, size);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@Valid @PathVariable Long userId) {
        service.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
