package AUI.Lab.schools.DTO;

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
public class ReadAllSchool {

    @Singular
    private List<School> schools;

    public static Function<List<AUI.Lab.schools.entity.School>, ReadAllSchool> entityToDtoMapper() {
        return schools -> {
            ReadAllSchoolBuilder response = ReadAllSchool.builder();
            schools.stream()
                    .map(school -> School.builder()
                            .schoolName(school.getSchoolName())
                            .build())
                    .forEach(response::school);
            return response.build();
        };
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @SuperBuilder
    public static class School {
        private String schoolName;
    }

}
