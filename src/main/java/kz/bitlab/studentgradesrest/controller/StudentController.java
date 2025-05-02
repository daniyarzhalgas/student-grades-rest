package kz.bitlab.studentgradesrest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Students API", description = "Manage students and their grades")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Create new student with optional grades")
    @PostMapping
    public Student createStudent(@RequestBody @Valid CreateStudentDTO dto) {
        List<Grade> grades = new ArrayList<>();
        if (dto.getGrades() != null) {
            grades = dto.getGrades().stream()
                    .map(g -> new Grade(g.getSubject(), g.getQuiz(), g.getMidterm1(), g.getMidterm2()))
                    .collect(Collectors.toList());
        }

        Student student = new Student(null, dto.getName(), dto.getEmail(), grades);
        return studentService.addStudent(student);
    }

    @Operation(summary = "Get all students with their grades")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Get a specific student by ID")
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Operation(summary = "Add a new grade to an existing student")
    @PutMapping("/{id}/grades")
    public void addGrade(@PathVariable Long id, @RequestBody @Valid GradeDTO dto) {
        Grade grade = new Grade(dto.getSubject(), dto.getQuiz(), dto.getMidterm1(), dto.getMidterm2());
        studentService.addGradeToStudent(id, grade);
    }

    @Operation(summary = "Get top 3 students by average grade")
    @GetMapping("/top")
    public List<StudentWithAverageDTO> getTopStudents() {
        return studentService.getTopStudentsWithAverage(3);
    }

    @Operation(summary = "Get top 3 students by grade in a specific subject")
    @GetMapping("/top/{subject}")
    public List<StudentGradeDTO> getTop3StudentsBySubject(@PathVariable String subject) {
        return studentService.getTop3StudentsBySubject(subject);
    }


}

