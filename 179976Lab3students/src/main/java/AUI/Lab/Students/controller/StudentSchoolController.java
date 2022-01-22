package AUI.Lab.Students.controller;


import AUI.Lab.Schools.entity.School;
import AUI.Lab.Schools.services.SchoolService;
import AUI.Lab.Students.DTO.CreateStudent;
import AUI.Lab.Students.DTO.ReadAllStudent;
import AUI.Lab.Students.DTO.ReadStudent;
import AUI.Lab.Students.DTO.UpdateStudent;
import AUI.Lab.Students.entity.Student;
import AUI.Lab.Students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/schools/{schoolName}/students")
public class StudentSchoolController {

    private StudentService studentService;

    private SchoolService schoolService;

    @Autowired
    public StudentSchoolController(StudentService studentService, SchoolService schoolService) {
        this.studentService = studentService;
        this.schoolService = schoolService;
    }


    @GetMapping
    public ResponseEntity<ReadAllStudent> ReadAllStudent(@PathVariable("schoolName") String name) {
        Optional<School> school = schoolService.find(name);
        return school.map(value -> ResponseEntity.ok(ReadAllStudent.entityToDtoMapper()
                        .apply(studentService.findAll(school.get()))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("{studentId}")
    public ResponseEntity<ReadStudent> ReadStudent(@PathVariable("studentId") int id) {
        return studentService.find(id)
                .map(value -> ResponseEntity.ok(ReadStudent.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> createStudent(@PathVariable("schoolName") String sname,
                                              @RequestBody CreateStudent request,
                                              UriComponentsBuilder builder) {
        Optional<School> school = schoolService.find(sname);
        if (school.isPresent()) {
            Student student = CreateStudent
                    .dtoToEntityMapper(name -> schoolService.find(name).orElseThrow())
                    .apply(request);
            student = studentService.save(student);
            return ResponseEntity.created(builder.pathSegment("api", "schools", "{schoolName}", "students", "{studentId}")
                    .buildAndExpand(school.get().getSchoolName(), student.getStudentId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("schoolName") String sname,
                                              @PathVariable("studentId") int studentId) {
        Optional<Student> student = studentService.find(sname, studentId);
        if (student.isPresent()) {
            studentService.delete(student.get().getStudentId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable("schoolName") String sname,
                                              @RequestBody UpdateStudent request,
                                              @PathVariable("studentId") int id) {
        Optional<Student> student = studentService.find(sname, id);
        if (student.isPresent()) {
            UpdateStudent.dtoToEntityUpdater().apply(student.get(), request);
            studentService.update(student.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
