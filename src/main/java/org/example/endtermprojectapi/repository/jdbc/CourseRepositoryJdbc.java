package org.example.endtermprojectapi.repository.jdbc;

import org.example.endtermprojectapi.model.Course;
import org.example.endtermprojectapi.repository.CourseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryJdbc implements CourseRepository {

    private final JdbcTemplate jdbc;

    public CourseRepositoryJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int create(Course c) {
        return jdbc.update("INSERT INTO courses(name, credits) VALUES (?, ?)", c.getName(), c.getCredits());
    }

    @Override
    public List<Course> getAll() {
        return jdbc.query(
                "SELECT id, name, credits FROM courses ORDER BY id",
                (rs, rowNum) -> new Course(rs.getInt("id"), rs.getString("name"), rs.getInt("credits"))
        );
    }

    @Override
    public Optional<Course> getById(int id) {
        List<Course> list = jdbc.query(
                "SELECT id, name, credits FROM courses WHERE id=?",
                (rs, rowNum) -> new Course(rs.getInt("id"), rs.getString("name"), rs.getInt("credits")),
                id
        );
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public int update(int id, Course c) {
        return jdbc.update("UPDATE courses SET name=?, credits=? WHERE id=?", c.getName(), c.getCredits(), id);
    }

    @Override
    public int delete(int id) {
        return jdbc.update("DELETE FROM courses WHERE id=?", id);
    }
}
