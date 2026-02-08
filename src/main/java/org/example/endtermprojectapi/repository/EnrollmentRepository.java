package org.example.endtermprojectapi.repository;

public interface EnrollmentRepository {
    boolean studentExists(int studentId);
    boolean courseExists(int courseId);
    boolean enrollmentExists(int studentId, int courseId);

    int enroll(int studentId, int courseId);
    int deleteByStudentAndCourse(int studentId, int courseId);
}
