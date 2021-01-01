package name.ilhan.student.controller;

import io.swagger.annotations.ApiOperation;
import name.ilhan.student.dto.StudentInput;
import name.ilhan.student.model.Student;
import name.ilhan.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get students")
    public ResponseEntity<Page<Student>> getStudents(Pageable pageable) {
        Page<Student> students = studentService.getStudents(pageable);
        return ResponseEntity.ok().body(students);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a student")
    public ResponseEntity<Student> postStudent(StudentInput studentInput) {
        Student student = studentService.createStudent(studentInput);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a contact")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }
}
