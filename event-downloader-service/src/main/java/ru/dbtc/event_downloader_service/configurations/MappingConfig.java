package ru.dbtc.event_downloader_service.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dbtc.event_downloader_service.dto.EventDto;
import ru.dbtc.event_downloader_service.models.Event;
import ru.dbtc.event_downloader_service.utils.convertors.CategoriesListConvertor;

@Configuration
public class MappingConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(EventDto.class, Event.class)
                .addMapping(eventDto -> eventDto.getLocation().getAddress(), Event::setAddress)
                .addMapping(eventDto -> eventDto.getLocation().getCity(), Event::setCity)
                .addMapping(eventDto -> eventDto.getLocation().getCountry(), Event::setCountry)
                .addMappings(mapper -> mapper.using(new CategoriesListConvertor()).map(EventDto::getCategories, Event::setCategories));
        return modelMapper;
    }
}
