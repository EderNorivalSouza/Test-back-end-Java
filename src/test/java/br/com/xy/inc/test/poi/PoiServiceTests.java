package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.PoiApplication;
import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.repository.PoiRepository;
import br.com.xy.inc.poi.service.PoiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = PoiApplication.class)
@ActiveProfiles("test")
class PoiServiceTests {
	@Autowired
	PoiRepository poiRepository;
	@Autowired
	PoiServices services;
	@Test
	public void filterTest(){
		PoiFilterDto form = new PoiFilterDto(30,30,20);
		List<Poi> list = services.filteredPois(poiRepository.findAll(),form);
		poiRepository.findAll().forEach(poi -> System.out.println(poi.toString()));
		assertEquals(3,list.size());

	}
	@Test
	public void findByIdTest(){
		Poi referencePoi = new Poi("Test 4", 40, 40);
		poiRepository.findAll().forEach(poi -> System.out.println(poi.toString()));
		Poi testPoi = services.findById(4L);
		assertEquals(referencePoi.getName(),testPoi.getName());
		assertEquals(referencePoi.getCoordX(),testPoi.getCoordX());
		assertEquals(referencePoi.getCoordY(),testPoi.getCoordY());
	}
}
