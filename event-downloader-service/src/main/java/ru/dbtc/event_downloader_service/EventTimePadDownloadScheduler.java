package ru.dbtc.event_downloader_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@PropertySource("classpath:timepad.properties")
public class EventTimePadDownloadScheduler implements EventDownloadScheduler{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${timepad.token}")
    private String token;

    // Test connect
    public String getTimepadEvents() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return restTemplate.exchange(RequestEntity.get("https://api.timepad.ru/v1/events.json")
                .headers(headers)
                .build(), String.class)
                .getBody();
    }

    @Scheduled(cron = "* 0/30 * * * ?")
    @Override
    public void downloadEvents() {
        System.out.println("HELLO");
    }
}
