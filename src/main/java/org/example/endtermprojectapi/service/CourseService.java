package org.example.endtermprojectapi.service;

import org.example.endtermprojectapi.dto.CourseRequest;
import org.example.endtermprojectapi.model.Course;

import java.util.List;

public interface CourseService {
    Course create(CourseRequest req);
    List<Course> getAll();
    Course getById(int id);
    Course update(int id, CourseRequest req);
    void delete(int id);
}
