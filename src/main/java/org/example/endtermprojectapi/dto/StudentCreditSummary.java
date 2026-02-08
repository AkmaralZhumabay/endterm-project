package org.example.endtermprojectapi.dto;

public class StudentCreditSummary {
    public int studentId;
    public String name;
    public String email;
    public int totalCredits;

    public StudentCreditSummary(int studentId, String name, String email, int totalCredits) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.totalCredits = totalCredits;
    }
}
