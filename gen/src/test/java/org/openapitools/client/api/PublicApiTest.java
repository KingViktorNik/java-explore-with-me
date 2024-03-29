/*
 * Main service API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.ApiError;
import org.openapitools.client.model.CategoryDto;
import org.openapitools.client.model.CompilationDto;
import org.openapitools.client.model.EventFullDto;
import org.openapitools.client.model.EventShortDto;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PublicApi
 */
@Ignore
public class PublicApiTest {

    private final PublicApi api = new PublicApi();

    
    /**
     * Получение категорий
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getCategoriesTest() throws ApiException {
        Integer from = null;
        Integer size = null;
        List<CategoryDto> response = api.getCategories(from, size);

        // TODO: test validations
    }
    
    /**
     * Получение информации о категории по её идентификатору
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getCategoryTest() throws ApiException {
        Long catId = null;
        CategoryDto response = api.getCategory(catId);

        // TODO: test validations
    }
    
    /**
     * Получение подборки событий по его id
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getCompilationTest() throws ApiException {
        Long compId = null;
        CompilationDto response = api.getCompilation(compId);

        // TODO: test validations
    }
    
    /**
     * Получение подборок событий
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getCompilationsTest() throws ApiException {
        Boolean pinned = null;
        Integer from = null;
        Integer size = null;
        List<CompilationDto> response = api.getCompilations(pinned, from, size);

        // TODO: test validations
    }
    
    /**
     * Получение подробной информации об опубликованном событии по его идентификатору
     *
     * Обратите внимание: - событие должно быть опубликовано - информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов - информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getEvent1Test() throws ApiException {
        Long id = null;
        EventFullDto response = api.getEvent1(id);

        // TODO: test validations
    }
    
    /**
     * Получение событий с возможностью фильтрации
     *
     * Обратите внимание:  - это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события - текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв - если в запросе не указан диапазон дат [rangeStart-rangeEnd], то нужно выгружать события, которые произойдут позже текущей даты и времени - информация о каждом событии должна включать в себя количество просмотров и количество уже одобренных заявок на участие - информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getEvents1Test() throws ApiException {
        String text = null;
        List<Long> categories = null;
        Boolean paid = null;
        String rangeStart = null;
        String rangeEnd = null;
        Boolean onlyAvailable = null;
        String sort = null;
        Integer from = null;
        Integer size = null;
        List<EventShortDto> response = api.getEvents1(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);

        // TODO: test validations
    }
    
}
