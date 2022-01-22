package AUI.Lab.Students.DTO;

import AUI.Lab.Schools.entity.School;
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
public class CreateStudent {

    private String name;

    private String surname;

    private String email;

    private String school;

    public static Function<CreateStudent, Student> dtoToEntityMapper(Function<String, School> schoolFunction) {
        return CreateStudent -> Student.builder()
                .name(CreateStudent.getName())
                .surname(CreateStudent.getSurname())
                .email(CreateStudent.getEmail())
                .school(schoolFunction.apply(CreateStudent.getSchool()))
                .build();
    }
}
