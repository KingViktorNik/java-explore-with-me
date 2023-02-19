package ru.practicum.ewm.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@AllArgsConstructor
public class CompilationNewDto {
    /** Список идентификаторов событий входящих в подборку **/
    @NotNull(message = "can not be null")
    private Set<Long> events;

    /** Закреплена ли подборка на главной странице сайта **/
    @NotNull(message = "can not be null")
    private Boolean pinned;

    /** Заголовок подборки **/
    @NotNull(message = "can not be null")
    @NotBlank(message = "can not be empty")
    private String title;

}
