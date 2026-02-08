package org.example.endtermprojectapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CourseRequest {
    @NotBlank public String name;
    @Min(1) public int credits;
}
