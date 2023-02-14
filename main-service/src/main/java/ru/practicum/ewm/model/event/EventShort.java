package ru.practicum.ewm.model.event;

import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.model.User;
import ru.practicum.ewm.model.event.enums.EventState;

import java.time.LocalDateTime;

public interface EventShort {
/** Идентификатор **/
    Long getId();

/** Краткое описание **/
    String getAnnotation();

/** Краткое описание **/
    String getDescription();

/** Категория **/
    Category getCategory();

/** Количество одобренных заявок на участие в данном событии **/
    Integer getConfirmedRequests();

/** Статус события **/
    EventState getState();

/** Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss") **/
    LocalDateTime getEventDate();

/** Пользователь (инициатора) **/
    User getInitiator();

/** Нужно ли оплачивать участие **/
    Boolean getPaid();

/** Заголовок **/
    String getTitle();

/** Количество просмотрев события **/
    Integer getViews();
}
