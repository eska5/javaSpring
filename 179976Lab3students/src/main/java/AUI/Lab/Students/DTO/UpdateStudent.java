package AUI.Lab.Students.DTO;

import AUI.Lab.Students.entity.Student;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateStudent {

    private String name;
    private String surname;
    private String email;


    public static BiFunction<Student, UpdateStudent, Student> dtoToEntityUpdater() {
        return (student, UpdateStudent) -> {
            student.setName(UpdateStudent.getName());
            student.setSurname(UpdateStudent.getSurname());
            student.setEmail(UpdateStudent.getEmail());
            return student;
        };
    }
}
