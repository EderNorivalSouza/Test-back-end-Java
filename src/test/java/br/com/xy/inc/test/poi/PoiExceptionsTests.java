package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.PoiApplication;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.model.PoiFormDto;
import br.com.xy.inc.poi.repository.PoiRepository;
import org.assertj.core.error.AssertJMultipleFailuresError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = PoiApplication.class)
public class PoiExceptionsTests {

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
    public void exceptionFilterDtoWhenFilterApply(){
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            restTemplate = new RestTemplate();
            PoiFilterDto form = new PoiFilterDto(0, 0, -20);
            ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/pois/filter", form, String.class);
//        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
        });
    }
    @Test
    public void exceptionFormDtoWhenAddPoi(){
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            restTemplate = new RestTemplate();
            PoiFormDto form = new PoiFormDto("", 0, 0);
            ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/pois", form, String.class);
//        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
        });
    }
}
