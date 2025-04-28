package kz.bitlab.studentgradesrest.models;


public class Grade {
    private String subject;
    private Double gradeValue;

    // Constructors
    public Grade() {
    }

    public Grade(String subject, Double gradeValue) {
        this.subject = subject;
        this.gradeValue = gradeValue;
    }

    // Getters and Setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Double gradeValue) {
        this.gradeValue = gradeValue;
    }
}
