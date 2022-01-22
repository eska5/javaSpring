package AUI.Lab.Students.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ReadAllStudent {

    @Singular
    private List<Student> students;

    public static Function<List<AUI.Lab.Students.entity.Student>, ReadAllStudent> entityToDtoMapper() {
        return students -> {
            ReadAllStudentBuilder response = ReadAllStudent.builder();
            students.stream()
                    .map(student -> Student.builder()
                            .student_id(student.getStudentId())
                            .name(student.getName())
                            .surname(student.getSurname())
                            .build())
                    .forEach(response::student);
            return response.build();
        };
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @SuperBuilder
    public static class Student {


        private int student_id;
        private String name;
        private String surname;
    }

}
