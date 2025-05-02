package kz.bitlab.studentgradesrest.service;

import jakarta.annotation.PostConstruct;
import kz.bitlab.studentgradesrest.dto.StudentGradeDTO;
import kz.bitlab.studentgradesrest.dto.StudentGradeInfoDTO;
import kz.bitlab.studentgradesrest.dto.StudentWithAverageDTO;
import kz.bitlab.studentgradesrest.models.Grade;
import kz.bitlab.studentgradesrest.models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
        grades1.add(new Grade("Mechanics", 85.0, 90.0, 88.0));
        grades1.add(new Grade("Thermodynamics", 80.0, 85.0, 82.0));
        grades1.add(new Grade("Optics", 70.0, 55.0, 72.0));
        addStudent(new Student(null, "Ailana", "ailana@stu.sdu.edu.kz", grades1));

        List<Grade> grades2 = new ArrayList<>();
        grades2.add(new Grade("Mechanics", 70.0, 75.0, 78.0));
        grades2.add(new Grade("Thermodynamics", 85.0, 88.0, 87.0));
        grades2.add(new Grade("Optics", 70.0, 55.0, 72.0));
        addStudent(new Student(null, "Nursaule", "nursaule@stu.sdu.edu.kz", grades2));

        List<Grade> grades3 = new ArrayList<>();
        grades3.add(new Grade("Mechanics", 70.0, 75.0, 78.0));
        grades3.add(new Grade("Thermodynamics", 85.0, 88.0, 87.0));
        grades3.add(new Grade("Optics", 70.0, 55.0, 72.0));
        addStudent(new Student(null, "Zhaniye", "zhaniye@stu.sdu.edu.kz", grades3));

        List<Grade> grades4 = new ArrayList<>();
        grades4.add(new Grade("Mechanics", 70.0, 75.0, 78.0));
        grades4.add(new Grade("Thermodynamics", 85.0, 88.0, 87.0));
        grades4.add(new Grade("Optics", 70.0, 55.0, 72.0));
        addStudent(new Student(null, "Dias", "dias@stu.sdu.edu.kz", grades4));
    }


    public Student addStudent(Student student) {
        student.setId(nextId++);
        if (student.getGrades() == null)
            student.setGrades(new ArrayList<>());
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
                            .mapToDouble(g -> average(g))
                            .average()
                            .orElse(0.0);
                    return new StudentWithAverageDTO(s.getId(), s.getName(), s.getEmail(), avg);
                })
                .sorted((s1, s2) -> Double.compare(s2.getAverageGrade(), s1.getAverageGrade()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<StudentGradeDTO> getTop3StudentsBySubject(String subject) {
        return students.stream()
                .map(student -> {
                    Optional<Grade> gradeOpt = student.getGrades().stream()
                            .filter(g -> g.getSubject().equalsIgnoreCase(subject))
                            .findFirst();
                    return gradeOpt.map(grade -> new StudentGradeDTO(
                            student.getName(),
                            student.getEmail(),
                            average(grade)  // Средняя оценка по предмету
                    ));
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted((g1, g2) -> Double.compare(g2.getGradeValue(), g1.getGradeValue())) // по убыванию
                .limit(3)
                .collect(Collectors.toList());
    }


    private double average(Grade grade) {
        return (grade.getQuiz() + grade.getMidterm1() + grade.getMidterm2()) / 3.0;
    }


    public List<StudentGradeInfoDTO> getAllGradesBySubject(String subject) {
        return students.stream()
                .flatMap(student -> student.getGrades().stream()
                        .filter(g -> g.getSubject().equalsIgnoreCase(subject))
                        .map(g -> new StudentGradeInfoDTO(
                                student.getName(),
                                student.getEmail(),
                                g.getSubject(),
                                g.getQuiz(),
                                g.getMidterm1(),
                                g.getMidterm2()
                        ))
                )
                .collect(Collectors.toList());
    }


}
