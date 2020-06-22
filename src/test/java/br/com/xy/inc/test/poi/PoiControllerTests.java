package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.PoiApplication;
import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.model.PoiFormDto;
import br.com.xy.inc.poi.repository.PoiRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = PoiApplication.class)
@ActiveProfiles(value = "test")
public class PoiControllerTests {

    @Autowired
    PoiRepository poiRepository;
    private RestTemplate restTemplate;

    @Test
    public void requisitionGetTestStatus() {
        restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity("http://localhost:8080/pois", String.class);
//        poiRepository.findAll().forEach(poi -> System.out.println(poi.toString()));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    @Test
    public void requisitionGetList() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/pois", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Poi> list = objectMapper.readValue(response, List.class);
//		poiRepository.findAll().forEach(poi -> System.out.println(poi.toString()));
        assertEquals(7, list.size());
    }
    @Test
    public void requisitionPostFilterStatus() {
        restTemplate = new RestTemplate();
        PoiFilterDto form = new PoiFilterDto(30, 30, 20);
        ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/pois/filter", form, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    @Test
    public void requisitionPostFiltercheck() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        PoiFilterDto form = new PoiFilterDto(30, 30, 20);
        ObjectMapper objectMapper = new ObjectMapper();
        String response = restTemplate.postForObject("http://localhost:8080/pois/filter", form, String.class);
        List<Poi> list = objectMapper.readValue(response, List.class);
        assertEquals(3, list.size());
    }
    @Test
    public void requisitionPostStatus() {
        restTemplate = new RestTemplate();
        PoiFormDto form = new PoiFormDto("Teste", 25, 25);
        ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/pois", form, String.class);
        //print response poi from POST requisiton
        System.out.println(response.getBody().toString());
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    @Test 
    public void requisitionPostCheckValue() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        PoiFormDto form = new PoiFormDto("Teste", 25, 25);
        ObjectMapper objectMapper = new ObjectMapper();
        String response = restTemplate.postForObject("http://localhost:8080/pois", form, String.class);
        System.out.println(response);
        Poi poi = objectMapper.readValue(response, Poi.class);
        System.out.println(poi.getName());
        System.out.println(poi.getCoordX());
        System.out.println(poi.getCoordY());
        assertEquals(form.getName(), poi.getName());
        assertEquals(form.getCoordX(), poi.getCoordX());
        assertEquals(form.getCoordY(), poi.getCoordY());
    }

    @AfterEach
    public void cleanTrash(){
        for(Poi deletePoi:poiRepository.findAll()){
            if(deletePoi.getCoordX()==25&&deletePoi.getCoordY()==25){
                poiRepository.delete(deletePoi);
            }
        }
    }
}