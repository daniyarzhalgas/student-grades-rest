package kz.bitlab.studentgradesrest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class GradeDTO {

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotNull @Min(0) @Max(100)
    private Double quiz;

    @NotNull @Min(0) @Max(100)
    private Double midterm1;

    @NotNull @Min(0) @Max(100)
    private Double midterm2;

    // Getters and Setters
    // ...


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getQuiz() {
        return quiz;
    }

    public void setQuiz(Double quiz) {
        this.quiz = quiz;
    }

    public Double getMidterm1() {
        return midterm1;
    }

    public void setMidterm1(Double midterm1) {
        this.midterm1 = midterm1;
    }

    public Double getMidterm2() {
        return midterm2;
    }

    public void setMidterm2(Double midterm2) {
        this.midterm2 = midterm2;
    }
}
