package com.supryaga.core.meeting.submission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyBookingRequest {

    private LocalTime startTime;
    private LocalTime endTime;
    private List<BookingRequest> bookingRequests;

}
