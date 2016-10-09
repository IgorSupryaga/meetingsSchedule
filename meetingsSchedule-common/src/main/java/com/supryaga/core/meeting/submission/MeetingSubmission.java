package com.supryaga.core.meeting.submission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingSubmission {

    private String employeeId;
    private LocalDateTime submissionTime;

}
