package AUI.Lab.schools.DTO;

import AUI.Lab.schools.entity.School;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateSchool {

    private String country;
    private int year_of_foundation;


    public static BiFunction<School, UpdateSchool, School> dtoToEntityUpdater() {
        return (school, UpdateSchool) -> {
            school.setCountry(UpdateSchool.getCountry());
            school.setYear_of_foundation(UpdateSchool.getYear_of_foundation());
            return school;
        };
    }
}
