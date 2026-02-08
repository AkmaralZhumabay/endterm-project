package org.example.endtermprojectapi.model;

public abstract class BaseEntity {
    protected Integer id;
    protected String name;

    protected BaseEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract String getType();
    public abstract String getDetails();

    public String basicInfo() {
        return id + ": " + name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
