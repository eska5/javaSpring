package AUI.Lab.schools.DTO;

import AUI.Lab.schools.entity.School;
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

    public static Function<CreateSchool, School> DtoToEntityMapper() {
        return CreateSchool -> School.builder()
                .schoolName(CreateSchool.getSchoolName())
                .country(CreateSchool.getCountry())
                .year_of_foundation(CreateSchool.getYear_of_foundation())
                .build();
    }
}
