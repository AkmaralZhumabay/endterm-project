package org.example.endtermprojectapi.model;

public class Student extends BaseEntity {
    private String email;
    private Address address; // composition

    public Student(Integer id, String name, String email, Address address) {
        super(id, name);
        this.email = email;
        this.address = address;
    }

    @Override public String getType() { return "Student"; }

    @Override public String getDetails() {
        String a = (address == null) ? "" : (" | " + address.getCity() + ", " + address.getStreet());
        return name + " (" + email + ")" + a;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
