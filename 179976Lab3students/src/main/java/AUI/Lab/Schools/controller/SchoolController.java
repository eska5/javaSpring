package AUI.Lab.Schools.controller;


import AUI.Lab.Schools.DTO.CreateSchool;
import AUI.Lab.Schools.entity.School;
import AUI.Lab.Schools.services.SchoolService;
import AUI.Lab.Students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/schools")
public class SchoolController {

    private final SchoolService schoolService;
    private final StudentService studentService;

    @Autowired
    public SchoolController(SchoolService schoolService, StudentService studentService) {
        this.schoolService = schoolService;
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<Void> createSchool(@RequestBody CreateSchool request, UriComponentsBuilder builder) {
        School school = CreateSchool
                .dtoToEntityMapper()
                .apply(request);
        school = schoolService.save(school);
        return ResponseEntity.created(builder.pathSegment("api", "students", "{school_name}").buildAndExpand(school.getSchoolName()).toUri()).build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable("id") String id) {
        Optional<School> school = schoolService.find(id);
        if (school.isPresent()) {
            schoolService.delete(school.get().getSchoolName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
