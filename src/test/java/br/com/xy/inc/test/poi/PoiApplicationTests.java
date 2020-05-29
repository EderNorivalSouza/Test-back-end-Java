package br.com.xy.inc.test.poi;

import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.repository.PoiRepository;
import br.com.xy.inc.poi.service.PoiServices;
import br.com.xy.inc.poi.service.PoiServicesImpl;
import br.com.xy.inc.test.repository.RepositoryTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes =PoiApplicationTests.class)
class PoiApplicationTests {



	private PoiServices services = new PoiServicesImpl();


	public List<Poi> dataGenetor() {
		List<Poi> pois = new ArrayList <> ();
		pois.add(new Poi("Local 1", 10, 10));
		pois.add(new Poi("Local 2", 20, 20));
		pois.add(new Poi("Local 3", 30, 30));
		pois.add(new Poi("Local 4", 40, 40));
		pois.add(new Poi("Local 5", 50, 50));
		pois.add(new Poi("Local 6", 60, 60));

		return pois;
	}

	@Test
	public void whenListAll(){
		List<Poi> list = services.listPois(dataGenetor());
		assertEquals(6, list.size());
	}

	@Test
	public void whenFilteredApplied(){
		PoiFilterDto form = new PoiFilterDto(30,30,20);
		List<Poi> list = services.filteredPois(dataGenetor(),form);
		assertEquals(3,list.size());
	}

//	@Test
//	public void whenListAllRepository(){
//
//		List<Poi> list = poiRepository.findAll();
//		assertEquals(6, list.size());
//	}

}
