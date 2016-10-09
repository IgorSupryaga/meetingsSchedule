package com.supryaga.extractors;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class WorkingTimeExtractor implements Extractor {

    public LocalTime extract(String line) {
        StringBuilder time = new StringBuilder(line.substring(0, 2))
                .append(":")
                .append(line.substring(2, 4));

        return LocalTime.parse(time);
    }
}
