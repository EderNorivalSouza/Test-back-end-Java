package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.PoiApplication;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.model.PoiFormDto;
import br.com.xy.inc.poi.repository.PoiRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = PoiApplication.class)
@ActiveProfiles("test")
public class PoiExceptionsTests {
    @Autowired
    PoiRepository poiRepository;
    private RestTemplate restTemplate;
    @Test
    public void exceptionFilterDtoWhenFilterApply(){
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            restTemplate = new RestTemplate();
            PoiFilterDto form = new PoiFilterDto(0, 0, -20);
            ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/pois/filter", form, String.class);
        });
    }
    @Test
    public void exceptionFormDtoWhenAddPoi(){
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            restTemplate = new RestTemplate();
            PoiFormDto form = new PoiFormDto("", 0, 0);
            ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/pois", form, String.class);
        });
    }
}
