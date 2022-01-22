package AUI.Lab.Students.controller;

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
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;
    private final SchoolService schoolService;

    @Autowired
    public StudentController(StudentService studentService, SchoolService schoolService) {
        this.studentService = studentService;
        this.schoolService = schoolService;

    }

    @GetMapping
    public ResponseEntity<ReadAllStudent> getStudents() {
        return ResponseEntity.ok(ReadAllStudent.entityToDtoMapper().apply(studentService.findAll()));
    }

    @GetMapping("{studentId}")
    public ResponseEntity<ReadStudent> getStudent(@PathVariable("studentId") int id) {
        Optional<Student> student = studentService.find(id);
        return student.map(value -> ResponseEntity.ok(ReadStudent.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Void> createStudent(@RequestBody CreateStudent request, UriComponentsBuilder builder) {
        Student student = CreateStudent
                .dtoToEntityMapper(name -> schoolService.find(name).orElseThrow())
                .apply(request);
        student = studentService.save(student);
        return ResponseEntity.created(builder.pathSegment("api", "students", "{studentId}")
                .buildAndExpand(student.getStudentId()).toUri()).build();

    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") int id) {
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            studentService.delete(student.get().getStudentId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{studentId}")
    public ResponseEntity<Void> updateStudent(@RequestBody UpdateStudent request, @PathVariable("studentId") int id) {
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            UpdateStudent.dtoToEntityUpdater().apply(student.get(), request);
            studentService.update(student.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
