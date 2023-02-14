package ru.practicum.ewm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.practicum.ewm.client.stats.StatsClient;

@Configuration
public class StatsClientConfig {
    @Value("${spring.application.name}")
    private String application;

    @Value("${stats-server.port}")
    private String serverUri;


    @Bean
    StatsClient client() {
        return new StatsClient(application, serverUri);
    }
}
