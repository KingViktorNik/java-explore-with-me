package ru.practicum.ewm.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String name;
}
