package br.com.xy.inc.test.poi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.xy.inc.poi.repository.PoiRepository;
import br.com.xy.inc.poi.service.PoiServices;




class PoiApplicationTests {
	
	@Autowired
	private PoiRepository poiRepository;
	@Autowired
	PoiServices services;
	
	@Test
	public void whenListAllPois() {
//		List<Poi> pois = new ArrayList<Poi>();
//		pois.add(new Poi("Local 1", 10, 10));
//		pois.add(new Poi("Local 2", 20, 20));
//		pois.add(new Poi("Local 3", 30, 30));
//		pois.add(new Poi("Local 4", 40, 40));
//		pois.add(new Poi("Local 5", 50, 50));
//		pois.add(new Poi("Local 6", 60, 60));
		
//		entityManager.persist(pois);
//		entityManager.flush();
		
//		List<Poi> list = poiRepository.findAll();
		
//		assertEquals(7, list.size());
	}
}
