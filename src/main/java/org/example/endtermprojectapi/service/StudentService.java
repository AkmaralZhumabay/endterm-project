package org.example.endtermprojectapi.service;

import org.example.endtermprojectapi.dto.StudentCreditSummary;
import org.example.endtermprojectapi.dto.StudentRequest;
import org.example.endtermprojectapi.model.Student;

import java.util.List;

public interface StudentService {
    Student create(StudentRequest req);
    List<Student> getAll();
    Student getById(int id);
    Student update(int id, StudentRequest req);
    void delete(int id);
    StudentCreditSummary topByCredits();
}
