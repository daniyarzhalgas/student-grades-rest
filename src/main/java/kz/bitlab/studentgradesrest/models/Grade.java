package kz.bitlab.studentgradesrest.models;

public class Grade {
    private String subject;
    private Double quiz;
    private Double midterm1;
    private Double midterm2;

    // Constructors
    public Grade() {
    }

    public Grade(String subject, Double quiz, Double midterm1, Double midterm2) {
        this.subject = subject;
        this.quiz = quiz;
        this.midterm1 = midterm1;
        this.midterm2 = midterm2;
    }

    // Getters and Setters
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

    // Optional: average grade
    public double getAverage() {
        return (quiz + midterm1 + midterm2) / 3;
    }
}
