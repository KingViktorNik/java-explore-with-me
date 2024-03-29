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

/**
 * Широта и долгота места проведения события
 */
@ApiModel(description = "Широта и долгота места проведения события")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-02-14T01:34:10.555586600+06:00[GMT+06:00]")
public class Location {
  public static final String SERIALIZED_NAME_LAT = "lat";
  @SerializedName(SERIALIZED_NAME_LAT)
  private Float lat;

  public static final String SERIALIZED_NAME_LON = "lon";
  @SerializedName(SERIALIZED_NAME_LON)
  private Float lon;


  public Location lat(Float lat) {
    
    this.lat = lat;
    return this;
  }

   /**
   * Широта
   * @return lat
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "55.754167", value = "Широта")

  public Float getLat() {
    return lat;
  }


  public void setLat(Float lat) {
    this.lat = lat;
  }


  public Location lon(Float lon) {
    
    this.lon = lon;
    return this;
  }

   /**
   * Долгота
   * @return lon
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "37.62", value = "Долгота")

  public Float getLon() {
    return lon;
  }


  public void setLon(Float lon) {
    this.lon = lon;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(this.lat, location.lat) &&
        Objects.equals(this.lon, location.lon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lat, lon);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Location {\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
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

