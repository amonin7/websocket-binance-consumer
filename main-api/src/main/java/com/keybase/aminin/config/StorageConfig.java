package com.keybase.aminin.config;

import com.keybase.aminin.storage.TickersHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    // 2 ^ 16 would be a default value
    @Value("${max.prices.amount.per.ticker:65536}")
    private int maxHeapSize;

    @Bean
    public TickersHashMap getTickersHashMap() {
        return new TickersHashMap(maxHeapSize);
    }
}
