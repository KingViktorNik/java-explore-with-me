package ru.practicum.ewm.server.stats.service;

import ru.practicum.ewm.dto.stats.dto.EndpointHitDto;
import ru.practicum.ewm.dto.stats.dto.StatsAnswerDto;

import java.util.List;

public interface StatsService {
    /**
     * Сохранение информации о том, что на uri конкретного сервиса был отправлен запрос пользователем.
     *
     * @param endpointHitDto Название сервиса, uri и ip пользователя указаны в теле запроса.
     * @return CODE 200
     */
    EndpointHitDto saveHit(EndpointHitDto endpointHitDto);

    /**
     * Получение статистики по посещениям.
     *
     * @param start  Дата и время начала диапазона за который нужно выгрузить статистику (в формате "yyyy-MM-dd HH:mm:ss")
     * @param end    Дата и время конца диапазона за который нужно выгрузить статистику (в формате "yyyy-MM-dd HH:mm:ss")
     * @param uris   Список uri для которых нужно выгрузить статистику
     * @param unique Нужно ли учитывать только уникальные посещения (только с уникальным ip) Default value : false
     * @return ViewStats - app - Название сервиса, uri - URI сервиса, hits - Количество просмотров
     */
    List<StatsAnswerDto> getStats(String start, String end, List<String> uris, Boolean unique);

}
