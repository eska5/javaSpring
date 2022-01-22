package AUI.Lab.Students.services;

import AUI.Lab.Schools.entity.School;
import AUI.Lab.Schools.repositories.SchoolRepository;
import AUI.Lab.Students.entity.Student;
import AUI.Lab.Students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository repository;

    private SchoolRepository schoolRepository;

    @Autowired
    public StudentService(StudentRepository repository, SchoolRepository schoolRepository) {
        this.repository = repository;
        this.schoolRepository = schoolRepository;
    }

    public Optional<Student> find(int checkId) {
        return repository.findById(checkId);
    }

    public Optional<Student> find(String username, int studentId) {
        Optional<School> school = schoolRepository.findBySchoolName(username);
        if (school.isPresent()) {
            return repository.findBySchoolAndStudentId(school.get(), studentId);
        } else {
            return Optional.empty();
        }
    }


    public List<Student> findAll() {
        return repository.findAll();
    }

    public List<Student> findAll(School school) {
        return repository.findBySchool(school);
    }


    @Transactional
    public Student save(Student newStudent) {
        return repository.save(newStudent);

    }

    @Transactional
    public void delete(int checkId) {
        repository.deleteById(checkId);
    }

    @Transactional
    public void update(Student newStudent) {
        repository.save(newStudent);
    }

}
