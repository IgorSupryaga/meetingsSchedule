package com.supryaga.core.meeting.submission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingSchedule {

    private LocalDateTime requestTime;
    private Duration duration;

    public boolean isOverlapedBy(MeetingSchedule meetingSchedule) {
        LocalDateTime endTime = this.requestTime.plus(this.duration);
        LocalDateTime startTime2 = meetingSchedule.getRequestTime();
        LocalDateTime endTime2 = meetingSchedule.getRequestTime().plus(meetingSchedule.getDuration());

        return !(this.requestTime.compareTo(startTime2) <= 0 && endTime.compareTo(startTime2) <= 0 ||
                this.requestTime.compareTo(startTime2) >=0 && this.requestTime.compareTo(endTime2) >= 0);
    }

}
