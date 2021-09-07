package ru.dbtc.event_downloader_service.utils.convertors;

import org.modelmapper.AbstractConverter;
import ru.dbtc.event_downloader_service.dto.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriesListConvertor extends AbstractConverter<List<CategoryDto>, List<String>> {
    @Override
    protected List<String> convert(List<CategoryDto> categories) {
        return categories
                .stream()
                .map(CategoryDto::getName)
                .collect(Collectors.toList());
    }
}
