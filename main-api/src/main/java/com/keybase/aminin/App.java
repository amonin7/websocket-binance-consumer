package com.keybase.aminin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(20_000);
        SpringApplication.run(App.class, args);
    }
}
