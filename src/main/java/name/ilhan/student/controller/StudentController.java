package name.ilhan.student.controller;

import io.swagger.annotations.ApiOperation;
import name.ilhan.student.dto.Pagination;
import name.ilhan.student.dto.StudentInput;
import name.ilhan.student.model.Student;
import name.ilhan.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get students")
    public ResponseEntity<Page<Student>> getStudents(Pagination pagination) {
        Page<Student> students = studentService.getStudents(pagination.getPageNo(), pagination.getPageSize());
        return ResponseEntity.ok().body(students);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a student")
    public ResponseEntity<Student> postStudent(
            @RequestBody
            StudentInput studentInput
    ) {
        Student student = studentService.createStudent(studentInput);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a contact")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fully update a student")
    public ResponseEntity<Student> putStudent(
            @PathVariable Integer id,
            StudentInput studentInput
    ) {
        Student student = studentService.fullyUpdateStudent(id, studentInput);
        return ResponseEntity.ok(student);
    }
}
