package kz.bitlab.studentgradesrest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class GradeDTO {

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotNull(message = "Grade value is required")
    @Min(value = 0, message = "Grade must be at least 0")
    @Max(value = 100, message = "Grade must be at most 100")
    private Double gradeValue;

    // Getters and setters
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Double getGradeValue() { return gradeValue; }
    public void setGradeValue(Double gradeValue) { this.gradeValue = gradeValue; }
}
