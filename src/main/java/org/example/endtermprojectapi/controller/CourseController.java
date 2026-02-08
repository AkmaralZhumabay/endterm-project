package org.example.endtermprojectapi.controller;

import jakarta.validation.Valid;
import org.example.endtermprojectapi.dto.CourseRequest;
import org.example.endtermprojectapi.model.Course;
import org.example.endtermprojectapi.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public Course create(@Valid @RequestBody CourseRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable int id, @Valid @RequestBody CourseRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
