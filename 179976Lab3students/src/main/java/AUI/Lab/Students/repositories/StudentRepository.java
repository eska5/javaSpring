package AUI.Lab.Students.repositories;


import AUI.Lab.Schools.entity.School;
import AUI.Lab.Students.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByStudentId(int studentId);

    List<Student> findBySchool(School school);

    Optional<Student> findBySchoolAndStudentId(School school, int studentId);
}
