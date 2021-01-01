package name.ilhan.student.service;

import name.ilhan.student.dto.StudentInput;
import name.ilhan.student.model.Student;
import name.ilhan.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
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
