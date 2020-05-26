package br.com.xy.inc.poi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.repository.PoiRepository;

@Service
public class PoiServicesImpl implements PoiServices {

	@Autowired
	PoiRepository poiRepository;


		public List<Poi> listPois(){
			List<Poi> pois = poiRepository.findAll();
			return pois;
		}

							
		public List<Poi> filteredPois (PoiFilterDto form){
			List <Poi> pois = poiRepository.findAll();
			List <Poi> filteredPois = new ArrayList<Poi>();	
				for (Poi poi:pois) {
					int x = Math.abs(poi.getCoordX()-form.getCoordX());
					int y = Math.abs(poi.getCoordY()-form.getCoordY());
						if((x+y)<=form.getDmax()){
							filteredPois.add(poi);
						}
				}
				return filteredPois;
		}
		
		public void addPoi(Poi poi){
			poiRepository.save(poi);
		}
}
