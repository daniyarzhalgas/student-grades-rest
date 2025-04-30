package kz.bitlab.studentgradesrest.dto;

public class StudentGradeDTO {
    private String name;
    private String email;
    private double gradeValue;

    public StudentGradeDTO(String name, String email, double gradeValue) {
        this.name = name;
        this.email = email;
        this.gradeValue = gradeValue;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getGradeValue() { return gradeValue; }
}
