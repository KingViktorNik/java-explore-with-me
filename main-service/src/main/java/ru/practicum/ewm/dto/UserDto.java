package ru.practicum.ewm.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    /** Идентификатор */
    private Long id;

    /** Почтовый адрес */
    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    @Email(message = "Email should be valid")
    private String email;

    /** Имя пользователя */
    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String name;

}
