package org.example.endtermprojectapi.repository;

import org.example.endtermprojectapi.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    int create(Course c);
    List<Course> getAll();
    Optional<Course> getById(int id);
    int update(int id, Course c);
    int delete(int id);
}
