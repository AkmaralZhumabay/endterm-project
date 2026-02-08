package org.example.endtermprojectapi.service.impl;

import org.example.endtermprojectapi.dto.StudentCreditSummary;
import org.example.endtermprojectapi.dto.StudentRequest;
import org.example.endtermprojectapi.exception.DuplicateResourceException;
import org.example.endtermprojectapi.exception.InvalidInputException;
import org.example.endtermprojectapi.exception.ResourceNotFoundException;
import org.example.endtermprojectapi.model.Student;
import org.example.endtermprojectapi.patterns.builder.StudentBuilder;
import org.example.endtermprojectapi.patterns.factory.BaseEntityFactory;
import org.example.endtermprojectapi.repository.StudentRepository;
import org.example.endtermprojectapi.service.StudentService;
import org.example.endtermprojectapi.utils.EmailUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student create(StudentRequest req) {
        if (req.name == null || req.name.isBlank()) throw new InvalidInputException("Student name cannot be empty");
        if (!EmailUtils.isValid(req.email)) throw new InvalidInputException("Invalid email format");
        if (repo.emailExists(req.email)) throw new DuplicateResourceException("Student with this email already exists");

        // Builder pattern
        Student s = new StudentBuilder()
                .name(req.name)
                .email(req.email)
                .address(req.city, req.street)
                .build();

        // Factory pattern (proof call)
        BaseEntityFactory.createStudent(req.name, req.email, req.city, req.street);

        repo.create(s);

        // Return created student (simple way: find by email)
        return repo.getAll().stream()
                .filter(x -> x.getEmail().equalsIgnoreCase(req.email))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Created student not found"));
    }

    @Override
    public List<Student> getAll() {
        return repo.getAll();
    }

    @Override
    public Student getById(int id) {
        return repo.getById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found: id=" + id));
    }

    @Override
    public Student update(int id, StudentRequest req) {
        Student existing = getById(id);

        if (req.name == null || req.name.isBlank()) throw new InvalidInputException("Student name cannot be empty");
        if (!EmailUtils.isValid(req.email)) throw new InvalidInputException("Invalid email format");

        if (!existing.getEmail().equalsIgnoreCase(req.email) && repo.emailExists(req.email)) {
            throw new DuplicateResourceException("Student with this email already exists");
        }

        Student updated = new StudentBuilder()
                .id(id)
                .name(req.name)
                .email(req.email)
                .address(req.city, req.street)
                .build();

        int rows = repo.update(id, updated);
        if (rows == 0) throw new ResourceNotFoundException("Student not found: id=" + id);

        return getById(id);
    }

    @Override
    public void delete(int id) {
        getById(id);
        int rows = repo.delete(id);
        if (rows == 0) throw new ResourceNotFoundException("Student not found: id=" + id);
    }

    @Override
    public StudentCreditSummary topByCredits() {
        return repo.topStudentByCredits();
    }
}
