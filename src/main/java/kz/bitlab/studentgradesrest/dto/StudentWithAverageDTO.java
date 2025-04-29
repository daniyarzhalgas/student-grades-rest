package kz.bitlab.studentgradesrest.dto;


public class StudentWithAverageDTO {
    private Long id;
    private String name;
    private String email;
    private double averageGrade;

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

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public StudentWithAverageDTO(Long id, String name, String email, double averageGrade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.averageGrade = averageGrade;
    }

    // Getters and setters
}
