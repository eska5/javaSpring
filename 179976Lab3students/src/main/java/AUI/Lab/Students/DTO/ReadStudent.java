package AUI.Lab.Students.DTO;

import AUI.Lab.Students.entity.Student;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ReadStudent {

    private int student_id;
    private String name;
    private String surname;
    private String email;
    private String school;

    public static Function<Student, ReadStudent> entityToDtoMapper() {
        return student -> ReadStudent.builder()
                .student_id(student.getStudentId())
                .name(student.getName())
                .surname(student.getSurname())
                .email(student.getEmail())
                .school(student.getSchool().getSchoolName())
                .build();
    }
}
