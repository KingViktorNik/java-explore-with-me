package ru.practicum.ewm.dto.partRequest;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartRequestDto {
    /** Идентификатор заявки **/
    private Long id;

    /** Дата и время создания заявки **/
    @NotNull(message = "can not be null")
    private String created;

    /** Идентификатор события **/
    private Long event;

    /** Идентификатор пользователя, отправивший заявку **/
    @NotNull(message = "can not be null")
    private Long requester;

    /** Статус заявки **/
    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String status;
}
