package name.ilhan.student.model;

import lombok.Data;

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

    public Student(Integer id, String firstName, String lastName, Date dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public Student() {
    }
}
