package name.ilhan.student.service;

import name.ilhan.student.enums.Gender;
import name.ilhan.student.model.Student;
import name.ilhan.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldReturnFindAll() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Name", "Surname", new GregorianCalendar(2021, Calendar.JANUARY, 3).getTime(), Gender.MALE));
        studentList.add(new Student(2, "Name2", "Surname2", new GregorianCalendar(2021, Calendar.JANUARY, 4).getTime(), Gender.MALE));
        Page<Student> studentPage = new PageImpl<Student>(studentList);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        given(studentRepository.findAll(pageable)).willReturn(studentPage);

        Page<Student> expected = studentService.getStudents(0, 10);
        assertEquals(expected.getContent(), studentList);
    }

}
