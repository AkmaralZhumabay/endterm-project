package org.example.endtermprojectapi.service;

import org.example.endtermprojectapi.dto.EnrollmentRequest;

public interface EnrollmentService {
    void enroll(EnrollmentRequest req);
    void remove(EnrollmentRequest req);
}
