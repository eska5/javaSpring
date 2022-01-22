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
public class ReadSchool {

    private String schoolName;
    private String country;
    private int year_of_foundation;


    public static Function<School, ReadSchool> entityToDtoMapper() {
        return school -> ReadSchool.builder()
                .schoolName(school.getSchoolName())
                .country(school.getCountry())
                .year_of_foundation(school.getYear_of_foundation())
                .build();
    }
}
