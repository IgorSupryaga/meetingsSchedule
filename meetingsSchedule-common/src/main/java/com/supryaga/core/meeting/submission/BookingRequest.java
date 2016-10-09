package com.supryaga.core.meeting.submission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private MeetingSubmission meetingSubmission;
    private MeetingSchedule meetingSchedule;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingRequest that = (BookingRequest) o;

        if (!meetingSubmission.equals(that.meetingSubmission)) return false;
        return meetingSchedule.equals(that.meetingSchedule);

    }

    @Override
    public int hashCode() {
        int result = meetingSubmission != null ? meetingSubmission.hashCode() : 0;
        result = 31 * result + (meetingSchedule != null ? meetingSchedule.hashCode() : 0);
        return result;
    }
}
