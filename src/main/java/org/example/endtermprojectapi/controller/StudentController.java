package org.example.endtermprojectapi.controller;

import jakarta.validation.Valid;
import org.example.endtermprojectapi.dto.StudentCreditSummary;
import org.example.endtermprojectapi.dto.StudentRequest;
import org.example.endtermprojectapi.model.Student;
import org.example.endtermprojectapi.patterns.singleton.AppConfigSingleton;
import org.example.endtermprojectapi.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Singleton proof endpoint
    @GetMapping("/app")
    public String appName() {
        return AppConfigSingleton.getInstance().getAppName();
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public Student create(@Valid @RequestBody StudentRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable int id, @Valid @RequestBody StudentRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/top-credits")
    public StudentCreditSummary topCredits() {
        return service.topByCredits();
    }
}
