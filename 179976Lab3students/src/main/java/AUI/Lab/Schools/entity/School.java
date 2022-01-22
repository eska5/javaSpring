package AUI.Lab.Schools.entity;

import AUI.Lab.Students.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "schools")
public class School {
    @Id
    @Column(unique = true)
    private String schoolName;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    @Basic(fetch = FetchType.LAZY)
    private List<Student> student;


}
