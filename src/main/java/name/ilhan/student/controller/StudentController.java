package name.ilhan.student.controller;

import io.swagger.annotations.ApiOperation;
import name.ilhan.student.dto.StudentInput;
import name.ilhan.student.model.Student;
import name.ilhan.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all students")
    public String getStudents() {
        return "List of students";
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a student")
    public ResponseEntity<Student> postStudent(StudentInput studentInput) {
        Student student = studentService.createStudent(studentInput);
        return ResponseEntity.ok(student);
    }
}
