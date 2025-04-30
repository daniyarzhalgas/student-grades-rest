package kz.bitlab.studentgradesrest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CreateStudentDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

//    @NotNull(message = "Grades list cannot be null")
    private List<@Valid GradeDTO> grades;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<GradeDTO> getGrades() { return grades; }
    public void setGrades(List<GradeDTO> grades) { this.grades = grades; }
}
