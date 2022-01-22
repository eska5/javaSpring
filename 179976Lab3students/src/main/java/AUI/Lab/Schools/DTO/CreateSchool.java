package AUI.Lab.Schools.DTO;

import AUI.Lab.Schools.entity.School;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateSchool {

    private String schoolName;

    private String country;

    private int year_of_foundation;

    public static Function<CreateSchool, School> dtoToEntityMapper() {
        return CreateSchool -> School.builder()
                .schoolName(CreateSchool.getSchoolName())
                .build();
    }
}
