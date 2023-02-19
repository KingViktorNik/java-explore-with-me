package ru.practicum.ewm.dto.compilation;

import lombok.*;
import ru.practicum.ewm.dto.event.EventShortDto;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {
    /** Список событий входящих в подборку **/
    private List<EventShortDto> events;

    /** Идентификатор подборки **/
    private Long id;

    /** Закреплена подборка на главной странице сайта (true) **/
    private Boolean pinned;

    /** Заголовок подборки **/
    private String title;

}
