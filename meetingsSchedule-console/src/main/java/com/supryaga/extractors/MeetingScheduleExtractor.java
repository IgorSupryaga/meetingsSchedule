package com.supryaga.extractors;

import com.supryaga.core.meeting.submission.MeetingSchedule;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class MeetingScheduleExtractor implements Extractor {

    @Override
    public MeetingSchedule extract(String line) {
        MeetingSchedule meetingSchedule = new MeetingSchedule();
        String[] lineParts = line.split("\\s");
        LocalDate localDate = LocalDate.parse(lineParts[0]);
        LocalTime localTime = LocalTime.parse(lineParts[1]);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        meetingSchedule.setRequestTime(localDateTime);
        meetingSchedule.setDuration(Duration.ofHours(Integer.parseInt(lineParts[2])));

        return meetingSchedule;
    }
}
