package XY.Inc.POI.service;

import XY.Inc.POI.model.Poi;
import XY.Inc.POI.model.PoiFiltroDto;
import XY.Inc.POI.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Services {

	@Autowired
	PoiRepository poiRepository;

		public List<Poi> AplicaFiltro(List<Poi> pois, PoiFiltroDto form){	
			List<Poi> resultado = new ArrayList<Poi>();
			for (Poi poi:pois) {
				int x = Math.abs(poi.getCoordX()-form.getCoordX());
				int y = Math.abs(poi.getCoordY()-form.getCoordY());
					if((x+y)<=10){
						resultado.add(poi);
					}
			}
			return resultado;
		}

		public boolean validacao(PoiFiltroDto form){
			if(form.getCoordX()==null&&form.getCoordY()==null){
				return true;
			}
			else{
				return false;
			}
		}

		public List<Poi> listarPois(){
			List<Poi> pois = poiRepository.findAll();
			return pois;
		}
		public void adicionaPoi(Poi poi){
			poiRepository.save(poi);
		}
}
