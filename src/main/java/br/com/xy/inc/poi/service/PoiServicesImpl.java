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

		public List<Poi> listPois(List<Poi> poiList){
			List<Poi> list = poiList;
			return list;
		}

							
		public List<Poi> filteredPois (List<Poi>list, PoiFilterDto form){
			List <Poi> filteredPois = new ArrayList<Poi>();
				for (Poi poi:list) {
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

		public List<Poi> repositoryaccess() {
			return poiRepository.findAll();
		}
}
