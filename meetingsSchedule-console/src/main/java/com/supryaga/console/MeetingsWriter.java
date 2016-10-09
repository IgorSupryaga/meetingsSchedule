package com.supryaga.console;

import com.supryaga.core.meeting.submission.BookingRequest;
import com.supryaga.core.meeting.submission.MeetingSchedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingsWriter {
    private String fileName;

    public void writeInConsole(Map<LocalDate, List<BookingRequest>> result) {
        result.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            entry.getValue().forEach(
                    bookingRequest -> {
                        MeetingSchedule meetingSchedule = bookingRequest.getMeetingSchedule();
                        System.out.println(meetingSchedule.getRequestTime().toLocalTime() + " " +
                                meetingSchedule.getRequestTime().plus(meetingSchedule.getDuration()).toLocalTime() +
                                " " + bookingRequest.getMeetingSubmission().getEmployeeId());
                    }
            );
            System.out.println("====================================================================");
        });
    }

}
