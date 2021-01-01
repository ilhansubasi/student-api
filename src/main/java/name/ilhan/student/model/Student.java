package name.ilhan.student.model;

import lombok.Data;
import name.ilhan.student.enums.Gender;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    public Student(Integer id, String firstName, String lastName, Date dob, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
    }

    public Student() {
    }
}
