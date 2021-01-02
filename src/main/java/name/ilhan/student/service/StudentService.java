package name.ilhan.student.service;

import name.ilhan.student.dto.StudentInput;
import name.ilhan.student.model.Student;
import name.ilhan.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student createStudent(StudentInput studentInput) {
        Student student = new Student();
        student.setFirstName(studentInput.getFirstName());
        student.setLastName(studentInput.getLastName());
        student.setDob(studentInput.getDob());
        student.setGender(studentInput.getGender());
        return studentRepository.save(student);
    }

    public Page<Student> getStudents(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        return studentRepository.findAll(pageable);
    }

    public ArrayList<Student> getAllStudents() {
        var it = studentRepository.findAll();
        var students = new ArrayList<Student>();
        it.forEach(s -> students.add(s));
        return students;
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public Student fullyUpdateStudent(Integer id, StudentInput studentInput) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(studentInput.getFirstName());
        student.setLastName(studentInput.getLastName());
        student.setDob(studentInput.getDob());
        student.setGender(studentInput.getGender());
        return studentRepository.save(student);
    }
}
