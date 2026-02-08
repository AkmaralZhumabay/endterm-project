package org.example.endtermprojectapi.model;

public class Course extends BaseEntity {
    private int credits;

    public Course(Integer id, String name, int credits) {
        super(id, name);
        this.credits = credits;
    }

    @Override public String getType() { return "Course"; }

    @Override public String getDetails() {
        return name + " - " + credits + " credits";
    }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}
