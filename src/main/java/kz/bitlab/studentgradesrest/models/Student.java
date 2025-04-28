package kz.bitlab.studentgradesrest.models;
import java.util.List;

public class Student {
    private Long id;
    private String name;
    private String email;
    private List<Grade> grades; // ADD grades list

    // Constructors
    public Student() {
    }

    public Student(Long id, String name, String email, List<Grade> grades) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.grades = grades;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
