package AUI.Lab.DataInitializer;

import AUI.Lab.schools.entity.School;
import AUI.Lab.schools.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeData {


    private final SchoolService schoolService;

    @Autowired
    public InitializeData(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostConstruct
    private synchronized void init() {
        School pg = School.builder()
                .schoolName("Politechnika_Gdanska")
                .country("Poland")
                .year_of_foundation(1906)
                .build();

        School oxford = School.builder()
                .schoolName("University_of_Oxford")
                .country("England")
                .year_of_foundation(1096)
                .build();

        School massachusetts = School.builder()
                .schoolName("University_of_Massachusetts")
                .country("USA")
                .year_of_foundation(1863)
                .build();

        schoolService.save(pg);
        schoolService.save(oxford);
        schoolService.save(massachusetts);

    }

}
