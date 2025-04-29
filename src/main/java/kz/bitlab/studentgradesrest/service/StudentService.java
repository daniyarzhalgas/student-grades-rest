package kz.bitlab.studentgradesrest.service;

import jakarta.annotation.PostConstruct;
import kz.bitlab.studentgradesrest.dto.StudentWithAverageDTO;
import kz.bitlab.studentgradesrest.models.Grade;
import kz.bitlab.studentgradesrest.models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    @PostConstruct
    public void init() {
        List<Grade> grades1 = new ArrayList<>();
        grades1.add(new Grade("Math", 90.0));
        grades1.add(new Grade("Science", 85.5));
        addStudent(new Student(null, "Alice", "alice@example.com", grades1));

        List<Grade> grades2 = new ArrayList<>();
        grades2.add(new Grade("Math", 75.0));
        grades2.add(new Grade("Science", 88.0));
        addStudent(new Student(null, "Bob", "bob@example.com", grades2));

        List<Grade> grades3 = List.of(
                new Grade("Math", 95.0),
                new Grade("Science", 92.0),
                new Grade("English", 89.5)
        );
        addStudent(new Student(null, "Charlie", "charlie@example.com", grades3));

        List<Grade> grades4 = List.of(
                new Grade("Math", 60.0),
                new Grade("Science", 70.0),
                new Grade("History", 65.0)
        );
        addStudent(new Student(null, "David", "david@example.com", grades4));

        List<Grade> grades5 = List.of(
                new Grade("English", 82.0),
                new Grade("Science", 78.0),
                new Grade("Math", 84.0)
        );
        addStudent(new Student(null, "Eva", "eva@example.com", grades5));

        List<Grade> grades6 = List.of(
                new Grade("Math", 98.0),
                new Grade("Science", 97.5),
                new Grade("History", 96.0)
        );
        addStudent(new Student(null, "Fiona", "fiona@example.com", grades6));

        List<Grade> grades7 = List.of(
                new Grade("Math", 55.0),
                new Grade("Science", 58.0),
                new Grade("English", 60.0)
        );
        addStudent(new Student(null, "George", "george@example.com", grades7));
    }


    public Student addStudent(Student student) {
        student.setId(nextId++);
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> getStudentById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    public void addGradeToStudent(Long studentId, Grade newGrade) {
        Student student = getStudentById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.getGrades().add(newGrade);
    }


    public List<Grade> getGradesBySubject(String subject) {
        List<Grade> result = new ArrayList<>();
        for (Student student : students) {
            for (Grade grade : student.getGrades()) {
                if (grade.getSubject().equalsIgnoreCase(subject)) {
                    result.add(grade);
                }
            }
        }
        return result;
    }


    public List<StudentWithAverageDTO> getTopStudentsWithAverage(int topN) {
        return students.stream()
                .filter(s -> !s.getGrades().isEmpty())
                .map(s -> {
                    double avg = s.getGrades().stream()
                            .mapToDouble(Grade::getGradeValue)
                            .average()
                            .orElse(0.0);
                    return new StudentWithAverageDTO(s.getId(), s.getName(), s.getEmail(), avg);
                })
                .sorted((s1, s2) -> Double.compare(s2.getAverageGrade(), s1.getAverageGrade()))
                .limit(topN)
                .collect(Collectors.toList());
    }


}
