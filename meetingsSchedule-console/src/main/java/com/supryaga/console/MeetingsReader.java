package com.supryaga.console;

import com.supryaga.core.meeting.submission.BookingRequest;
import com.supryaga.core.meeting.submission.CompanyBookingRequest;
import com.supryaga.extractors.MeetingScheduleExtractor;
import com.supryaga.extractors.MeetingSubmissionExtractor;
import com.supryaga.extractors.WorkingTimeExtractor;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MeetingsReader {

    private String fileName;


    public MeetingsReader() {
    }

    public MeetingsReader(String fileName) {
        this.fileName = fileName;
    }

    public CompanyBookingRequest read() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        return readCompanyBookingRequest(bufferedReader);
    }

    private CompanyBookingRequest readCompanyBookingRequest(BufferedReader bufferedReader) {
        WorkingTimeExtractor workingTimeExtractor = new WorkingTimeExtractor();
        MeetingSubmissionExtractor meetingSubmissionExtractor = new MeetingSubmissionExtractor();
        MeetingScheduleExtractor meetingScheduleExtractor = new MeetingScheduleExtractor();
        CompanyBookingRequest companyBookingRequest = new CompanyBookingRequest();
        List<BookingRequest> bookingRequests = new ArrayList<>();
        try {
            String[] workingHoursArray = getWorkingHoursAsString(bufferedReader);
            LocalTime startWorkingTime = workingTimeExtractor.extract(workingHoursArray[0]);
            LocalTime endWorkingTime = workingTimeExtractor.extract(workingHoursArray[1]);
            companyBookingRequest.setStartTime(startWorkingTime);
            companyBookingRequest.setEndTime(endWorkingTime);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                BookingRequest bookingRequest = new BookingRequest();
                bookingRequest.setMeetingSubmission(meetingSubmissionExtractor.extract(line.trim()));
                bookingRequest.setMeetingSchedule(meetingScheduleExtractor.extract(bufferedReader.readLine().trim()));
                bookingRequests.add(bookingRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        companyBookingRequest.setBookingRequests(bookingRequests);

        return companyBookingRequest;
    }

    private String[] getWorkingHoursAsString(BufferedReader bufferedReader) {
        String[] workingHoursArray = new String[2];
        try {
            bufferedReader.readLine();
            bufferedReader.readLine();
            String workingHoursString = bufferedReader.readLine().trim();
            if (workingHoursString.split("\\s").length == 2) {
                workingHoursArray = workingHoursString.split("\\s");
            } else {
                throw new RuntimeException("Working time length should be 2");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return workingHoursArray;
    }

}
