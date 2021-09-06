package ru.dbtc.event_downloader_service.configurations;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.dbtc.event_downloader_service.utils.interceptors.RestTemplateHeaderForTimePadInterceptor;

@Configuration
public class WebConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        builder = builder.additionalInterceptors(new RestTemplateHeaderForTimePadInterceptor());
        return builder.build();
    }
}
