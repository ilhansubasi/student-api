package name.ilhan.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import name.ilhan.student.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StudentInput {
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private Gender gender;
}
