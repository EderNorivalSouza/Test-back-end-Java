package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.PoiApplication;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.model.PoiFormDto;
import br.com.xy.inc.poi.repository.PoiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = PoiApplication.class)
public class PoiControllerTests {

    @Autowired
    PoiRepository poiRepository;
    private RestTemplate restTemplate;

    @BeforeEach
    public void dataSetUp() {
        poiRepository.deleteAll();
        poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 1", 10, 10));
        poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 2", 20, 20));
        poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 3", 30, 30));
        poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 4", 40, 40));
        poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 5", 50, 50));
        poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 6", 60, 60));
    }

    @Test
    public void requisitionGetTestStatus(){
        restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity("http://localhost:8080/pois",String.class);
		poiRepository.findAll().forEach(poi -> System.out.println(poi.toString()));
        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
    }
    @Test
    public  void requisitionPostFilterStatus() {
        restTemplate = new RestTemplate();
        PoiFilterDto form = new PoiFilterDto(30,30,20);
        ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/pois/filter",form,String.class);
        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
    }

    @Test
    public  void requisitionPostStatus() {
        restTemplate = new RestTemplate();
        PoiFormDto form = new PoiFormDto("teste",25,25);
        ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/pois",form,String.class);
        //print response poi from POST requisiton
        System.out.println(response.getBody().toString());
        assertThat(response.getStatusCode(),equalTo(HttpStatus.CREATED));

    }


}
