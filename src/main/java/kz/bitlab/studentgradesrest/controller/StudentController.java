package kz.bitlab.studentgradesrest.controller;

import jakarta.validation.Valid;
import kz.bitlab.studentgradesrest.dto.CreateStudentDTO;
import kz.bitlab.studentgradesrest.dto.GradeDTO;
import kz.bitlab.studentgradesrest.dto.StudentGradeDTO;
import kz.bitlab.studentgradesrest.dto.StudentWithAverageDTO;
import kz.bitlab.studentgradesrest.models.Grade;
import kz.bitlab.studentgradesrest.models.Student;
import kz.bitlab.studentgradesrest.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody @Valid CreateStudentDTO dto) {
        List<Grade> grades;
        if (dto.getGrades() != null) {
            grades = dto.getGrades().stream()
                    .map(g -> new Grade(g.getSubject(), g.getGradeValue()))
                    .collect(Collectors.toList());
        } else {
            grades = new ArrayList<>();
        }


        Student student = new Student(null, dto.getName(), dto.getEmail(), grades);
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @PutMapping("/{id}/grades")
    public void addGrade(@PathVariable Long id, @RequestBody @Valid GradeDTO dto) {
        Grade grade = new Grade(dto.getSubject(), dto.getGradeValue());
        studentService.addGradeToStudent(id, grade);
    }

    @GetMapping("/top")
    public List<StudentWithAverageDTO> getTopStudents() {
        return studentService.getTopStudentsWithAverage(3);
    }

    @GetMapping("/top/{subject}")
    public List<StudentGradeDTO> getTop3StudentsBySubject(@PathVariable String subject) {
        return studentService.getTop3StudentsBySubject(subject);
    }


}

