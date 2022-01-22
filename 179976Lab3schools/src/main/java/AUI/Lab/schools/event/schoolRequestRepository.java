package AUI.Lab.schools.event;

import AUI.Lab.schools.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class schoolRequestRepository {

    private RestTemplate restTemplate;

    @Autowired
    public schoolRequestRepository(@Value("${project}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void deleteById(String school) {
        restTemplate.delete("/schools/{id}", school);
    }

    public void save(School school) {
        restTemplate.postForLocation("/schools", CreateSchoolRequest.EntityToDtoMapper().apply(school));
    }

}
