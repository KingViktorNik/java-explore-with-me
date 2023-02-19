package ru.practicum.ewm.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class CompilationUpdateDto {

    /** Список идентификаторов событий входящих в подборку **/
    private Set<Long> events;

    /** Закреплена ли подборка на главной странице сайта **/
    private Boolean pinned;

    /** Заголовок подборки **/
    private String title;
}
