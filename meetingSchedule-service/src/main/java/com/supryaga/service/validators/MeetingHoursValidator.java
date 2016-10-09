package com.supryaga.service.validators;


import com.supryaga.core.meeting.submission.BookingRequest;
import com.supryaga.core.meeting.submission.CompanyBookingRequest;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

// TODO: remove this stuff
public class MeetingHoursValidator {

    public List<BookingRequest> validate(final CompanyBookingRequest companyBookingRequest) {

        return companyBookingRequest.getBookingRequests().stream().filter(bookingRequest -> {
                    LocalTime startTime = bookingRequest.getMeetingSchedule().getRequestTime().toLocalTime();
                    LocalTime companyBookingRequestEndTime = companyBookingRequest.getEndTime();
                    Duration meetingDuration = bookingRequest.getMeetingSchedule().getDuration();
                    return startTime
                            .compareTo(companyBookingRequest.getStartTime()) >= 0 &&
                            Duration.between(startTime,
                                    companyBookingRequestEndTime).compareTo(meetingDuration) >= 0 &&
                            startTime.plus(meetingDuration).compareTo(companyBookingRequestEndTime) <= 0;
                }
        ).collect(Collectors.toList());
    }
}
