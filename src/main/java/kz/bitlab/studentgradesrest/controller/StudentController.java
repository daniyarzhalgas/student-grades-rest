package kz.bitlab.studentgradesrest.controller;

import kz.bitlab.studentgradesrest.dto.StudentGradeDTO;
import kz.bitlab.studentgradesrest.dto.StudentWithAverageDTO;
import kz.bitlab.studentgradesrest.models.Grade;
import kz.bitlab.studentgradesrest.models.Student;
import kz.bitlab.studentgradesrest.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
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
    public void addGrade(@PathVariable Long id, @RequestBody Grade grade) {
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

