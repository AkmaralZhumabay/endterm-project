package org.example.endtermprojectapi.repository.jdbc;

import org.example.endtermprojectapi.repository.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentRepositoryJdbc implements EnrollmentRepository {

    private final JdbcTemplate jdbc;

    public EnrollmentRepositoryJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean studentExists(int studentId) {
        Integer cnt = jdbc.queryForObject("SELECT COUNT(*) FROM students WHERE id=?", Integer.class, studentId);
        return cnt != null && cnt > 0;
    }

    @Override
    public boolean courseExists(int courseId) {
        Integer cnt = jdbc.queryForObject("SELECT COUNT(*) FROM courses WHERE id=?", Integer.class, courseId);
        return cnt != null && cnt > 0;
    }

    @Override
    public boolean enrollmentExists(int studentId, int courseId) {
        Integer cnt = jdbc.queryForObject(
                "SELECT COUNT(*) FROM enrollments WHERE student_id=? AND course_id=?",
                Integer.class, studentId, courseId
        );
        return cnt != null && cnt > 0;
    }

    @Override
    public int enroll(int studentId, int courseId) {
        return jdbc.update(
                "INSERT INTO enrollments(student_id, course_id) VALUES (?, ?)",
                studentId, courseId
        );
    }

    @Override
    public int deleteByStudentAndCourse(int studentId, int courseId) {
        return jdbc.update(
                "DELETE FROM enrollments WHERE student_id=? AND course_id=?",
                studentId, courseId
        );
    }
}
