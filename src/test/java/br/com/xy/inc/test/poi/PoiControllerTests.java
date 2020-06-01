package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.PoiApplication;
import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.repository.PoiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PoiApplication.class)
@EnableAutoConfiguration
public class PoiControllerTests {

    final String BASE_PATH = "http://localhost:8080";

    private PoiRepository poiRepository;
    private RestTemplate restTemplate;
    private ObjectMapper MAPPER = new ObjectMapper();

    @BeforeEach
    public void setUp()throws Exception {
        List<Poi> pois = new ArrayList<Poi>();
        poiRepository.deleteAll();
        pois.add(new br.com.xy.inc.poi.model.Poi("Local 1", 10, 10));
        pois.add(new br.com.xy.inc.poi.model.Poi("Local 2", 20, 20));
        pois.add(new br.com.xy.inc.poi.model.Poi("Local 3", 30, 30));
        pois.add(new br.com.xy.inc.poi.model.Poi("Local 4", 40, 40));
        pois.add(new br.com.xy.inc.poi.model.Poi("Local 5", 50, 50));
        pois.add(new br.com.xy.inc.poi.model.Poi("Local 6", 60, 60));
        poiRepository.saveAll(pois);
    }
    @Test
    public void testCase(){
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(BASE_PATH+"/pois", String.class);
        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
    }

}
