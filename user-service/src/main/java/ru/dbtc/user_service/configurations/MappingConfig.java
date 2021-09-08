package ru.dbtc.user_service.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class MappingConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
