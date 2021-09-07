package ru.dbtc.event_downloader_service.dto;

import lombok.Data;

@Data
public class LocationDto {
    private String country;
    private String city;
    private String address;
}
