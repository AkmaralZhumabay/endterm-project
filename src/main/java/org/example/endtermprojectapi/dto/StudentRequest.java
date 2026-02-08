package org.example.endtermprojectapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StudentRequest {
    @NotBlank public String name;
    @NotBlank @Email public String email;

    //address
    public String city;
    public String street;
}
