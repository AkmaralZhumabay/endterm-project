package org.example.endtermprojectapi.patterns.builder;

import org.example.endtermprojectapi.model.Address;
import org.example.endtermprojectapi.model.Student;

public class StudentBuilder {
    private Integer id;
    private String name;
    private String email;
    private Address address;

    public StudentBuilder id(Integer id) { this.id = id; return this; }
    public StudentBuilder name(String name) { this.name = name; return this; }
    public StudentBuilder email(String email) { this.email = email; return this; }

    public StudentBuilder address(String city, String street) {
        if (city == null || city.isBlank() || street == null || street.isBlank()) {
            this.address = null;
        } else {
            this.address = new Address(city, street);
        }
        return this;
    }

    public Student build() {
        return new Student(id, name, email, address);
    }
}
