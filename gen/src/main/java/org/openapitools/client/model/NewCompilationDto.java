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


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Подборка событий
 */
@ApiModel(description = "Подборка событий")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-02-14T01:34:10.555586600+06:00[GMT+06:00]")
public class NewCompilationDto {
  public static final String SERIALIZED_NAME_EVENTS = "events";
  @SerializedName(SERIALIZED_NAME_EVENTS)
  private Set<Long> events = null;

  public static final String SERIALIZED_NAME_PINNED = "pinned";
  @SerializedName(SERIALIZED_NAME_PINNED)
  private Boolean pinned = false;

  public static final String SERIALIZED_NAME_TITLE = "title";
  @SerializedName(SERIALIZED_NAME_TITLE)
  private String title;


  public NewCompilationDto events(Set<Long> events) {
    
    this.events = events;
    return this;
  }

  public NewCompilationDto addEventsItem(Long eventsItem) {
    if (this.events == null) {
      this.events = new LinkedHashSet<Long>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * Список идентификаторов событий входящих в подборку
   * @return events
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "[1,2,3]", value = "Список идентификаторов событий входящих в подборку")

  public Set<Long> getEvents() {
    return events;
  }


  public void setEvents(Set<Long> events) {
    this.events = events;
  }


  public NewCompilationDto pinned(Boolean pinned) {
    
    this.pinned = pinned;
    return this;
  }

   /**
   * Закреплена ли подборка на главной странице сайта
   * @return pinned
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "false", value = "Закреплена ли подборка на главной странице сайта")

  public Boolean getPinned() {
    return pinned;
  }


  public void setPinned(Boolean pinned) {
    this.pinned = pinned;
  }


  public NewCompilationDto title(String title) {
    
    this.title = title;
    return this;
  }

   /**
   * Заголовок подборки
   * @return title
  **/
  @ApiModelProperty(example = "Летние концерты", required = true, value = "Заголовок подборки")

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewCompilationDto newCompilationDto = (NewCompilationDto) o;
    return Objects.equals(this.events, newCompilationDto.events) &&
        Objects.equals(this.pinned, newCompilationDto.pinned) &&
        Objects.equals(this.title, newCompilationDto.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(events, pinned, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewCompilationDto {\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
    sb.append("    pinned: ").append(toIndentedString(pinned)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

