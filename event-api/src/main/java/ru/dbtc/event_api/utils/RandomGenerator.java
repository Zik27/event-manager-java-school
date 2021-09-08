package ru.dbtc.event_api.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomGenerator {
    private final Random generator = new Random();

    public int generateRndNumberInRange(int min, int max) {
        return generator.nextInt(max - min + 1) + min;
    }

    public <T> T getRandomElement(List<T> list) {
        int i = generateRndNumberInRange(0, list.size() - 1);
        return list.get(i);
    }
}
