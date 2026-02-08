package org.example.endtermprojectapi.repository.jdbc;

import org.example.endtermprojectapi.dto.StudentCreditSummary;
import org.example.endtermprojectapi.exception.DatabaseOperationException;
import org.example.endtermprojectapi.model.Address;
import org.example.endtermprojectapi.model.Student;
import org.example.endtermprojectapi.repository.StudentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryJdbc implements StudentRepository {

    private final JdbcTemplate jdbc;

    public StudentRepositoryJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int create(Student s) {
        try {
            KeyHolder kh = new GeneratedKeyHolder();

            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO students(name, email) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, s.getName());
                ps.setString(2, s.getEmail());
                return ps;
            }, kh);

            Number key = kh.getKey();
            if (key == null && kh.getKeys() != null) {
                Object idObj = kh.getKeys().get("id");
                if (idObj instanceof Number) key = (Number) idObj;
            }
            if (key == null) throw new DatabaseOperationException("No ID returned");

            int newId = key.intValue();

            if (s.getAddress() != null) {
                jdbc.update(
                        "INSERT INTO addresses(student_id, city, street) VALUES (?, ?, ?)",
                        newId, s.getAddress().getCity(), s.getAddress().getStreet()
                );
            }
            return 1;
        } catch (Exception e) {
            throw new DatabaseOperationException("Create student failed: " + e.getMessage());
        }
    }

    @Override
    public List<Student> getAll() {
        String sql =
                "SELECT s.id, s.name, s.email, a.city, a.street " +
                        "FROM students s LEFT JOIN addresses a ON a.student_id = s.id " +
                        "ORDER BY s.id";
        return jdbc.query(sql, (rs, rowNum) -> {
            String city = rs.getString("city");
            String street = rs.getString("street");
            Address addr = (city == null || street == null) ? null : new Address(city, street);
            return new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    addr
            );
        });
    }

    @Override
    public Optional<Student> getById(int id) {
        String sql =
                "SELECT s.id, s.name, s.email, a.city, a.street " +
                        "FROM students s LEFT JOIN addresses a ON a.student_id = s.id " +
                        "WHERE s.id = ?";
        List<Student> list = jdbc.query(sql, (rs, rowNum) -> {
            String city = rs.getString("city");
            String street = rs.getString("street");
            Address addr = (city == null || street == null) ? null : new Address(city, street);
            return new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    addr
            );
        }, id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public int update(int id, Student s) {
        int rows = jdbc.update(
                "UPDATE students SET name=?, email=? WHERE id=?",
                s.getName(), s.getEmail(), id
        );

        if (s.getAddress() != null) {
            jdbc.update(
                    "INSERT INTO addresses(student_id, city, street) VALUES (?, ?, ?) " +
                            "ON CONFLICT (student_id) DO UPDATE SET city=EXCLUDED.city, street=EXCLUDED.street",
                    id, s.getAddress().getCity(), s.getAddress().getStreet()
            );
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        return jdbc.update("DELETE FROM students WHERE id=?", id);
    }

    @Override
    public boolean emailExists(String email) {
        Integer cnt = jdbc.queryForObject("SELECT COUNT(*) FROM students WHERE email=?", Integer.class, email);
        return cnt != null && cnt > 0;
    }

    @Override
    public StudentCreditSummary topStudentByCredits() {
        String sql =
                "SELECT s.id, s.name, s.email, COALESCE(SUM(c.credits),0) AS total_credits " +
                        "FROM students s " +
                        "LEFT JOIN enrollments e ON e.student_id = s.id " +
                        "LEFT JOIN courses c ON c.id = e.course_id " +
                        "GROUP BY s.id, s.name, s.email " +
                        "ORDER BY total_credits DESC " +
                        "LIMIT 1";
        return jdbc.query(sql, rs -> {
            if (!rs.next()) return null;
            return new StudentCreditSummary(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("total_credits")
            );
        });
    }
}
