package ru.dbtc.event_downloader_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EventTimePadDownloadScheduler implements EventDownloadScheduler{
    @Autowired
    private RestTemplate restTemplate;

    private final String token = "0736656bcc376ae7c3e21a44fc860321fb802a77";

    // Test connect
    public String getTimepadEvents() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return restTemplate.exchange(RequestEntity.get("https://api.timepad.ru/v1/events.json")
                .headers(headers)
                .build(), String.class)
                .getBody();
    }

    @Override
    public void downloadEvents() {

    }
}
