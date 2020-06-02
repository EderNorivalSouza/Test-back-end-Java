package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.PoiApplication;
import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.model.PoiFormDto;
import br.com.xy.inc.poi.repository.PoiRepository;
import br.com.xy.inc.poi.service.PoiServices;
import br.com.xy.inc.poi.service.PoiServicesImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = PoiApplication.class)

class PoiServiceTests {
	@Autowired
	PoiRepository poiRepository;

	RestTemplate restTemplate;
	private PoiServices services = new PoiServicesImpl();


	@BeforeEach
	public void data(){
		poiRepository.deleteAll();
		poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 1", 10, 10));
		poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 2", 20, 20));
		poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 3", 30, 30));
		poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 4", 40, 40));
		poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 5", 50, 50));
		poiRepository.save(new br.com.xy.inc.poi.model.Poi("Local 6", 60, 60));
	}

	@Test
	public void filterTest(){
		PoiFilterDto form = new PoiFilterDto(30,30,20);
		List<Poi> list = services.filteredPois(poiRepository.findAll(),form);
		assertEquals(3,list.size());
//		poiRepository.findAll().forEach(poi -> System.out.println(poi.toString()));
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
	public  void requisitionPostFiltercheck() throws JsonProcessingException {
		restTemplate = new RestTemplate();
		PoiFilterDto form = new PoiFilterDto(30,30,20);
		ObjectMapper objectMapper = new ObjectMapper();
		String response = restTemplate.postForObject("http://localhost:8080/pois/filter",form,String.class);
		List<Poi> list = objectMapper.readValue(response, List.class);
		assertEquals(3,list.size());

	}
	@Test
	public  void requisitionPostCheckValue() throws JsonProcessingException {
		restTemplate = new RestTemplate();
		PoiFormDto form = new PoiFormDto("Teste",25,25);
		ObjectMapper objectMapper = new ObjectMapper();
		String response = restTemplate.postForObject("http://localhost:8080/pois",form, String.class);
//		System.out.println(response);
		Poi poi = objectMapper.readValue(response, Poi.class);
//		System.out.println(poi.getName());
//		System.out.println(poi.getCoordX());
//		System.out.println(poi.getCoordY());
		assertEquals(form.getName(),poi.getName());
		assertEquals(form.getCoordX(),poi.getCoordX());
		assertEquals(form.getCoordY(),poi.getCoordY());
	}


}
