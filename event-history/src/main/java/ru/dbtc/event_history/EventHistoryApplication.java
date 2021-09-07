package ru.dbtc.event_history;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EventHistoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventHistoryApplication.class, args);
    }

}
