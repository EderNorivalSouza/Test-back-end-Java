package br.com.xy.inc.poi.service;

import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiFilterDto;

import java.util.List;



public interface PoiServices {

	List<Poi> filteredPois(List<Poi> list,PoiFilterDto form);
	void addPoi(Poi poi);
	List<Poi> findAll();
    Poi findById(Long id);
}
	

