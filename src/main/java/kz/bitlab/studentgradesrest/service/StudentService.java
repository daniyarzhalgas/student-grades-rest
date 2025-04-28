package kz.bitlab.studentgradesrest.service;

import jakarta.annotation.PostConstruct;
import kz.bitlab.studentgradesrest.models.Grade;
import kz.bitlab.studentgradesrest.models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    @PostConstruct
    public void init() {
        // Create some students with grades
        List<Grade> grades1 = new ArrayList<>();
        grades1.add(new Grade("Math", 90.0));
        grades1.add(new Grade("Science", 85.5));

        List<Grade> grades2 = new ArrayList<>();
        grades2.add(new Grade("Math", 75.0));
        grades2.add(new Grade("Science", 88.0));

        addStudent(new Student(null, "Alice", "alice@example.com", grades1));
        addStudent(new Student(null, "Bob", "bob@example.com", grades2));
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
}
