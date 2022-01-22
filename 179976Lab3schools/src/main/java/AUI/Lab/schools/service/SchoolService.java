package AUI.Lab.schools.service;

import AUI.Lab.schools.entity.School;
import AUI.Lab.schools.event.schoolRequestRepository;
import AUI.Lab.schools.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private SchoolRepository repository;
    private schoolRequestRepository eventRepository;

    @Autowired
    public SchoolService(SchoolRepository repository, schoolRequestRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<School> find(String name) {
        return repository.findById(name);
    }

    public List<School> findAll() {
        return repository.findAll();
    }

    @Transactional
    public School save(School newSchool) {
        eventRepository.save(newSchool);
        return repository.save(newSchool);
    }

    @Transactional
    public void delete(String name) {
        eventRepository.deleteById(name);
        repository.deleteById(name);
    }


    @Transactional
    public void update(School newSchool) {
        repository.save(newSchool);
    }
}
