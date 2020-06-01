package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.PoiApplication;
import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiDto;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.repository.PoiRepository;
import br.com.xy.inc.poi.service.PoiServices;
import br.com.xy.inc.poi.service.PoiServicesImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = PoiApplication.class)
//@EnableAutoConfiguration
class PoiApplicationTests {
	@Autowired
	PoiRepository poiRepository;

	RestTemplate restTemplate;
	private PoiServices services = new PoiServicesImpl();
	private List<Poi> pois = new ArrayList <> ();

	@BeforeEach
	public void data(){
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
	public void filterTest(){
		PoiFilterDto form = new PoiFilterDto(30,30,20);
		List<Poi> list = services.filteredPois(poiRepository.findAll(),form);
		assertEquals(3,list.size());
//		poiRepository.findAll().forEach(poi -> System.out.println(poi.toString()));
	}

	@Test
	public void requisitionGetTestStatus(){
		restTemplate = new RestTemplate();
		ResponseEntity response = restTemplate.getForEntity("http://localhost:8080/pois",String.class);
//		poiRepository.findAll().forEach(poi -> System.out.println(poi.toString()));
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));

	}

	@Test
	public void requisitionGetList() throws JsonProcessingException {
		restTemplate = new RestTemplate();
		String response = restTemplate.getForObject("http://localhost:8080/pois",String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Poi> list = objectMapper.readValue(response, List.class);
//		poiRepository.findAll().forEach(poi -> System.out.println(poi.toString()));
// 		System.out.println(list.get(3));
		assertEquals(6,list.size());

	}

	@Test
	public  void requisitionPostFilterStatus() {
		restTemplate = new RestTemplate();
		PoiFilterDto form = new PoiFilterDto(30,30,20);
		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/pois/filter",form,String.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}

	@Test
	public  void requisitionPostFiltercheckValue() throws JsonProcessingException {
		restTemplate = new RestTemplate();
		PoiFilterDto form = new PoiFilterDto(30,30,20);
		ObjectMapper objectMapper = new ObjectMapper();
		String response = restTemplate.postForObject("http://localhost:8080/pois/filter",form,String.class);
		List<Poi> list = objectMapper.readValue(response, List.class);
		assertEquals(3,list.size());

	}


}
