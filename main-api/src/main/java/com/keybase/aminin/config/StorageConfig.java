package com.keybase.aminin.config;

import com.keybase.aminin.storage.TickersHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Bean
    public TickersHashMap getTickersHashMap() {
        return new TickersHashMap();
    }
}
