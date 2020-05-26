package br.com.xy.inc.poi.service;

import java.util.List;

import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiFilterDto;

public interface PoiServices {

	List<Poi> listPois();
	List<Poi> filteredPois(PoiFilterDto form);
	void addPoi(Poi poi);
			
}
	

