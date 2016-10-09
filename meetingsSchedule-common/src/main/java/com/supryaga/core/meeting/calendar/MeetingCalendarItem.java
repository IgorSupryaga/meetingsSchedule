package com.supryaga.core.meeting.calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingCalendarItem {

    private LocalTime meetingStartTime;
    private LocalTime meetingEndTime;
    private String employeeId;
}
