package br.com.xy.inc.poi.service;
import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoiServicesImpl implements PoiServices {
	@Autowired
	PoiRepository poiRepository;

		public List<Poi> filteredPois (List<Poi>list, PoiFilterDto form){
			List <Poi> filteredPois = new ArrayList<Poi>();
				for (Poi poi:list) {
						if((distanceCalc(poi,form))<=form.getDmax()){
							filteredPois.add(poi);
						}
				}
			return filteredPois;
		}

		public void addPoi(Poi poi){poiRepository.save(poi);}

		public List<Poi> findAll() {return poiRepository.findAll();}


		public Poi findById(Long id) {
			Poi poi = new Poi();
			for(Poi resultPoi:findAll()){
				if(resultPoi.getId().equals(id)){
						 poi = resultPoi;
				}

			}
			return poi;
		}

	private int distanceCalc(Poi poi,PoiFilterDto form){
			int x = Math.abs(poi.getCoordX()-form.getCoordX());
			int y = Math.abs(poi.getCoordY()-form.getCoordY());
			int distanceCalc = (x+y);
			return distanceCalc;
		}
}
