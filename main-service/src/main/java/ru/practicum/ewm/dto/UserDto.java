package ru.practicum.ewm.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String name;
}
