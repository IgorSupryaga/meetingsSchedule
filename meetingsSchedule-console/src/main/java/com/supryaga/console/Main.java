package com.supryaga.console;

import com.supryaga.core.meeting.submission.CompanyBookingRequest;
import com.supryaga.service.MeetingsScheduleService;

public class Main {

    public static void main(String[] args) {
        MeetingsReader meetingsReader = new MeetingsReader("input.txt");
        CompanyBookingRequest companyBookingRequest = meetingsReader.read();
        new MeetingsWriter().writeInConsole(new MeetingsScheduleService().process(companyBookingRequest));

    }
}
