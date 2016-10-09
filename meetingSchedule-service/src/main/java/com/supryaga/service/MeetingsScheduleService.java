package com.supryaga.service;

import com.supryaga.core.meeting.submission.BookingRequest;
import com.supryaga.core.meeting.submission.CompanyBookingRequest;
import com.supryaga.service.validators.MeetingHoursValidator;

import java.time.LocalDate;
import java.util.*;

public class MeetingsScheduleService {

    public Map<LocalDate, List<BookingRequest>> process(CompanyBookingRequest companyBookingRequest) {
        companyBookingRequest.setBookingRequests(new MeetingHoursValidator()
                .validate(companyBookingRequest));
        Map<LocalDate, List<BookingRequest>> meetingsMap = convert(companyBookingRequest);
        Map<LocalDate, List<BookingRequest>> resultMap = new HashMap<>();
        meetingsMap.entrySet().forEach(
                set -> {
                    List<BookingRequest> resultRequests = resultMap.getOrDefault(set.getKey(), new ArrayList<>());
                    set.getValue().forEach(
                            bookingRequest -> {
                                if (resultRequests == null && bookingRequest != null) {
                                    resultRequests.add(bookingRequest);
                                } else {
                                    Iterator iterator = resultRequests.iterator();
                                    boolean isAddingItemForbidden = false;
                                    while (iterator.hasNext()) {
                                        BookingRequest bookingRequest1 = (BookingRequest) iterator.next();
                                        isAddingItemForbidden = bookingRequest1.getMeetingSchedule()
                                                .isOverlapedBy(bookingRequest.getMeetingSchedule());
                                        if (isAddingItemForbidden && bookingRequest.getMeetingSubmission()
                                                .getSubmissionTime().isBefore(bookingRequest1
                                                        .getMeetingSubmission().getSubmissionTime())) {
                                            iterator.remove();
                                            isAddingItemForbidden = false;
                                            break;
                                        }
                                    }
                                    if (!isAddingItemForbidden) {
                                        resultRequests.add(bookingRequest);
                                    }
                                }
                            }
                    );
                    resultMap.put(set.getKey(), resultRequests);
                }
        );
        return resultMap;
    }

    private Map<LocalDate, List<BookingRequest>> convert(CompanyBookingRequest companyBookingRequest) {
        Map<LocalDate, List<BookingRequest>> map = new HashMap<>();
        companyBookingRequest.getBookingRequests().forEach(bookingRequest -> {
                    LocalDate meetingDate = bookingRequest.getMeetingSchedule().getRequestTime().toLocalDate();
                    if (map.containsKey(meetingDate)) {
                        List<BookingRequest> requests = map.get(meetingDate);
                        if (!requests.contains(bookingRequest)) {
                            requests.add(bookingRequest);
                            map.put(meetingDate, requests);
                        }
                    } else {
                        List<BookingRequest> requests = new ArrayList<>();
                        requests.add(bookingRequest);
                        map.put(meetingDate, requests);
                    }
                }
        );

        return map;
    }

}
