package kz.bitlab.studentgradesrest.dto;

public class StudentGradeInfoDTO {
    private String studentName;
    private String email;
    private String subject;
    private Double quiz;
    private Double midterm1;
    private Double midterm2;

    public StudentGradeInfoDTO(String studentName, String email, String subject,
                               Double quiz, Double midterm1, Double midterm2) {
        this.studentName = studentName;
        this.email = email;
        this.subject = subject;
        this.quiz = quiz;
        this.midterm1 = midterm1;
        this.midterm2 = midterm2;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
