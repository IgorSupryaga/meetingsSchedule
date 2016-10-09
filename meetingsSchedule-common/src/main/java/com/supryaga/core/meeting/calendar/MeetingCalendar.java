package com.supryaga.core.meeting.calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingCalendar {

    private LocalDate meetingDate;
    private List<MeetingCalendarItem> meetings;
}

