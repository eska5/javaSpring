package AUI.Lab.schools.repository;

import AUI.Lab.schools.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, String> {

    Optional<School> findById(String name);

    List<School> findAll();

}
