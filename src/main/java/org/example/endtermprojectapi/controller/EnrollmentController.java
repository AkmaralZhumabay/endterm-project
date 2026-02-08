package org.example.endtermprojectapi.controller;

import jakarta.validation.Valid;
import org.example.endtermprojectapi.dto.EnrollmentRequest;
import org.example.endtermprojectapi.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    public void enroll(@Valid @RequestBody EnrollmentRequest req) {
        service.enroll(req);
    }

    @DeleteMapping
    public void remove(@Valid @RequestBody EnrollmentRequest req) {
        service.remove(req);
    }
}
