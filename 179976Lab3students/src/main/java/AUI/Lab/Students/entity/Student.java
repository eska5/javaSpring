package AUI.Lab.Students.entity;

import AUI.Lab.Schools.entity.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "students")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int studentId;
    private String name;
    private String surname;
    private String email;
    @ManyToOne
    @JoinColumn(name = "school")
    @Basic(fetch = FetchType.LAZY)
    private School school;

}
