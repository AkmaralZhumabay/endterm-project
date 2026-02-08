package org.example.endtermprojectapi.patterns.factory;

import org.example.endtermprojectapi.model.Address;
import org.example.endtermprojectapi.model.BaseEntity;
import org.example.endtermprojectapi.model.Course;
import org.example.endtermprojectapi.model.Student;

public class BaseEntityFactory {

    public static BaseEntity createStudent(String name, String email, String city, String street) {
        Address address = (city == null || street == null) ? null : new Address(city, street);
        return new Student(null, name, email, address);
    }

    public static BaseEntity createCourse(String name, int credits) {
        return new Course(null, name, credits);
    }
}
