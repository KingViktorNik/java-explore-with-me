package ru.practicum.ewm.server.stats.model;

public interface StatsQueryView {
    String getApp();

    String getUri();

    Integer getHits();
}
