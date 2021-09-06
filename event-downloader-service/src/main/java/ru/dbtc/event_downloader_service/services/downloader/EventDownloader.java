package ru.dbtc.event_downloader_service.services.downloader;

import java.util.List;

public interface EventDownloader {
    <T> List<T> getEvents(long skipEvents, int limitInRequest);

    long getCountEvents();
}
