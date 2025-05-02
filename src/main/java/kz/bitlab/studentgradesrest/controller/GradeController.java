package kz.bitlab.studentgradesrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import kz.bitlab.studentgradesrest.dto.StudentGradeInfoDTO;
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

    @Operation(summary = "Get all grades by subject")
    @GetMapping("/subject/{subject}")
    public List<Grade> getGradesBySubject(@PathVariable String subject) {
        return studentService.getGradesBySubject(subject);
    }

    @Operation(summary = "Get all student grades for a specific subject with student information")
    @GetMapping("/{subject}")
    public List<StudentGradeInfoDTO> getGradesBySubjectWithStudentInfo(@PathVariable String subject) {
        return studentService.getAllGradesBySubject(subject);
    }

}
