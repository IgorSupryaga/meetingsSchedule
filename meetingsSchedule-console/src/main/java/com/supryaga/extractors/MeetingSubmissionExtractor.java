package com.supryaga.extractors;

import com.supryaga.core.meeting.submission.MeetingSubmission;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class MeetingSubmissionExtractor implements Extractor {

    @Override
    public MeetingSubmission extract(String line) {
        MeetingSubmission meetingSubmission = new MeetingSubmission();
        String[] lineParts = line.split("\\s");
        if (lineParts.length == 3) {
            LocalDate localDate = LocalDate.parse(lineParts[0]);
            LocalTime localTime = LocalTime.parse(lineParts[1]);
            meetingSubmission.setSubmissionTime(LocalDateTime.of(localDate, localTime));
            meetingSubmission.setEmployeeId(lineParts[2]);
        } else {
            throw new RuntimeException("Invalid submissions format");
        }

        return meetingSubmission;
    }
}
