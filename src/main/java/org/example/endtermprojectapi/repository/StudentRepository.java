package org.example.endtermprojectapi.repository;

import org.example.endtermprojectapi.dto.StudentCreditSummary;
import org.example.endtermprojectapi.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    int create(Student s);
    List<Student> getAll();
    Optional<Student> getById(int id);
    int update(int id, Student s);
    int delete(int id);

    boolean emailExists(String email);
    StudentCreditSummary topStudentByCredits();
}
