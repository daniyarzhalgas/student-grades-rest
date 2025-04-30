package kz.bitlab.studentgradesrest.controller;

import kz.bitlab.studentgradesrest.models.Grade;
import kz.bitlab.studentgradesrest.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final StudentService studentService;

    public GradeController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/subject/{subject}")
    public List<Grade> getGradesBySubject(@PathVariable String subject) {
        return studentService.getGradesBySubject(subject);
    }
}
