package AUI.Lab.schools.event;

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
public class CreateSchoolRequest {
    private String schoolName;

    private String country;

    private int year_of_foundation;

    public static Function<School, CreateSchoolRequest> EntityToDtoMapper() {
        return School -> CreateSchoolRequest.builder()
                .schoolName(School.getSchoolName())
                .build();
    }
}
