package AUI.Lab.schools.controller;


import AUI.Lab.schools.DTO.CreateSchool;
import AUI.Lab.schools.DTO.ReadAllSchool;
import AUI.Lab.schools.DTO.ReadSchool;
import AUI.Lab.schools.DTO.UpdateSchool;
import AUI.Lab.schools.entity.School;
import AUI.Lab.schools.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<ReadAllSchool> getSchools() {
        return ResponseEntity.ok(ReadAllSchool.entityToDtoMapper().apply(schoolService.findAll()));
    }

    @GetMapping("{schoolName}")
    public ResponseEntity<ReadSchool> getSchool(@PathVariable("schoolName") String id) {
        Optional<School> school = schoolService.find(id);
        return school.map(value -> ResponseEntity.ok(ReadSchool.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Void> createSchool(@RequestBody CreateSchool request, UriComponentsBuilder builder) {
        School school = CreateSchool
                .DtoToEntityMapper()
                .apply(request);
        school = schoolService.save(school);
        return ResponseEntity.created(builder.pathSegment("api", "students", "{schoolName}")
                .buildAndExpand(school.getSchoolName()).toUri()).build();

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

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSchool(@RequestBody UpdateSchool request, @PathVariable("id") String id) {
        Optional<School> school = schoolService.find(id);
        if (school.isPresent()) {
            UpdateSchool.dtoToEntityUpdater().apply(school.get(), request);
            schoolService.update(school.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
