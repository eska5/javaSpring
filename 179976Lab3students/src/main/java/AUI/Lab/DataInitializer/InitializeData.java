package AUI.Lab.DataInitializer;

import AUI.Lab.Schools.entity.School;
import AUI.Lab.Schools.services.SchoolService;
import AUI.Lab.Students.entity.Student;
import AUI.Lab.Students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeData {


    private final StudentService studentService;

    private final SchoolService schoolService;

    @Autowired
    public InitializeData(StudentService studentService, SchoolService schoolService) {
        this.studentService = studentService;
        this.schoolService = schoolService;
    }

    @PostConstruct
    private synchronized void init() {
        School pg = School.builder()
                .schoolName("Politechnika_Gdanska")
                .build();

        School oxford = School.builder()
                .schoolName("University_of_Oxford")
                .build();

        School massachusetts = School.builder()
                .schoolName("University_of_Massachusetts")
                .build();

        schoolService.save(pg);
        schoolService.save(oxford);
        schoolService.save(massachusetts);

        Student studentOne = Student.builder()
                .name("Jakub")
                .surname("Sachajko")
                .email("jakubSach@wp.pl")
                .school(pg)
                .build();

        Student studentTwo = Student.builder()
                .name("Jan")
                .surname("Kowalski")
                .email("janKow@gmail.com")
                .school(pg)
                .build();

        Student studentThree = Student.builder()
                .name("Andrzej")
                .surname("Sapkowski")
                .email("andSap@wp.pl")
                .school(pg)
                .build();

        Student studentFour = Student.builder()
                .name("Alex")
                .surname("Jones")
                .email("alexJones07@gmail.com")
                .school(massachusetts)
                .build();

        Student studentFive = Student.builder()
                .name("edward")
                .surname("nygma")
                .email("enygma@onet.pl")
                .school(massachusetts)
                .build();

        Student studentSix = Student.builder()
                .name("Jack")
                .surname("Pumpkin")
                .email("mail@o2.uk")
                .school(oxford)
                .build();

        studentService.save((studentOne));
        studentService.save((studentTwo));
        studentService.save((studentThree));
        studentService.save((studentFour));
        studentService.save((studentFive));
        studentService.save((studentSix));
    }

}
