package org.example.endtermprojectapi.dto;

import jakarta.validation.constraints.Min;

public class EnrollmentRequest {
    @Min(1) public int studentId;
    @Min(1) public int courseId;
}
