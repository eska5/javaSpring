package AUI.Lab.Schools.services;

import AUI.Lab.Schools.entity.School;
import AUI.Lab.Schools.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SchoolService {

    private SchoolRepository repository;

    @Autowired
    public SchoolService(SchoolRepository repository) {
        this.repository = repository;
    }

    public Optional<School> find(String name) {
        return repository.findBySchoolName(name);
    }

    @Transactional
    public School save(School newSchool) {
        return repository.save(newSchool);
    }

    @Transactional
    public void delete(String name) {
        repository.deleteById(name);
    }
}
