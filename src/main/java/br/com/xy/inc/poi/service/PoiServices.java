package br.com.xy.inc.poi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiFilterDto;


@Service
public interface PoiServices {

	List<Poi> listPois();
	List<Poi> filteredPois(PoiFilterDto form);
	void addPoi(Poi poi);
			
}
	

