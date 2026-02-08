package org.example.endtermprojectapi.service.impl;

import org.example.endtermprojectapi.dto.EnrollmentRequest;
import org.example.endtermprojectapi.exception.DuplicateResourceException;
import org.example.endtermprojectapi.exception.ResourceNotFoundException;
import org.example.endtermprojectapi.repository.EnrollmentRepository;
import org.example.endtermprojectapi.service.EnrollmentService;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository repo;

    public EnrollmentServiceImpl(EnrollmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public void enroll(EnrollmentRequest req) {
        if (!repo.studentExists(req.studentId)) throw new ResourceNotFoundException("Student not found: id=" + req.studentId);
        if (!repo.courseExists(req.courseId)) throw new ResourceNotFoundException("Course not found: id=" + req.courseId);
        if (repo.enrollmentExists(req.studentId, req.courseId)) throw new DuplicateResourceException("Student already enrolled in this course");

        repo.enroll(req.studentId, req.courseId);
    }

    @Override
    public void remove(EnrollmentRequest req) {
        int rows = repo.deleteByStudentAndCourse(req.studentId, req.courseId);
        if (rows == 0) throw new ResourceNotFoundException("Enrollment not found");
    }
}
