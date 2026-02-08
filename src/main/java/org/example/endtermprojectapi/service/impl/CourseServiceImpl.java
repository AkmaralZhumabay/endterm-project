package org.example.endtermprojectapi.service.impl;

import org.example.endtermprojectapi.dto.CourseRequest;
import org.example.endtermprojectapi.exception.InvalidInputException;
import org.example.endtermprojectapi.exception.ResourceNotFoundException;
import org.example.endtermprojectapi.model.Course;
import org.example.endtermprojectapi.patterns.factory.BaseEntityFactory;
import org.example.endtermprojectapi.repository.CourseRepository;
import org.example.endtermprojectapi.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;

    public CourseServiceImpl(CourseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Course create(CourseRequest req) {
        if (req.name == null || req.name.isBlank()) throw new InvalidInputException("Course name cannot be empty");
        if (req.credits <= 0) throw new InvalidInputException("Credits must be > 0");

        // Factory proof call
        BaseEntityFactory.createCourse(req.name, req.credits);

        repo.create(new Course(null, req.name, req.credits));

        // Return last inserted (simple: last row in list)
        List<Course> all = repo.getAll();
        return all.isEmpty() ? null : all.get(all.size() - 1);
    }

    @Override
    public List<Course> getAll() {
        return repo.getAll();
    }

    @Override
    public Course getById(int id) {
        return repo.getById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found: id=" + id));
    }

    @Override
    public Course update(int id, CourseRequest req) {
        getById(id);

        if (req.name == null || req.name.isBlank()) throw new InvalidInputException("Course name cannot be empty");
        if (req.credits <= 0) throw new InvalidInputException("Credits must be > 0");

        int rows = repo.update(id, new Course(id, req.name, req.credits));
        if (rows == 0) throw new ResourceNotFoundException("Course not found: id=" + id);

        return getById(id);
    }

    @Override
    public void delete(int id) {
        getById(id);
        int rows = repo.delete(id);
        if (rows == 0) throw new ResourceNotFoundException("Course not found: id=" + id);
    }
}
