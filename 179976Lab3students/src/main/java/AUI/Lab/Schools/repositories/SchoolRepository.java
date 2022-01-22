package AUI.Lab.Schools.repositories;

import AUI.Lab.Schools.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, String> {

    Optional<School> findBySchoolName(String schoolName);
}
