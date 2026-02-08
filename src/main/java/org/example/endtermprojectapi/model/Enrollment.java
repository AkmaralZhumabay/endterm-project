package org.example.endtermprojectapi.model;

public class Enrollment {
    private Integer id;
    private int studentId;
    private int courseId;

    public Enrollment(Integer id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Integer getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
}
