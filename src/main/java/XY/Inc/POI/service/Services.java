package XY.Inc.POI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import XY.Inc.POI.exceptions.ErroValidacaoFiltro;
import XY.Inc.POI.model.Poi;
import XY.Inc.POI.repository.PoiRepository;

@Service
public class Services {

	@Autowired
	PoiRepository poiRepository;


		public List<Poi> listarPois(Integer coordX, Integer coordY, Integer dmax){
			List<Poi> pois = poiRepository.findAll();
			if(coordX==null&&coordY==null&&dmax==null) {
				return pois;	
			}
			else {
				verificaDados(coordX, coordY, dmax);
							
				List<Poi> resultado = new ArrayList<Poi>();
				for (Poi poi:pois) {
					int x = Math.abs(poi.getCoordX()-coordX);
					int y = Math.abs(poi.getCoordY()-coordY);
						if((x+y)<=dmax){
							resultado.add(poi);
						}
				}
				return resultado;
			}
		}
				
		public void adicionaPoi(Poi poi){
			poiRepository.save(poi);
		}
		
		private void verificaDados(Integer coordX, Integer coordY, Integer dmax) {
			if(coordX<=0) {
				throw new ErroValidacaoFiltro("Coord Y deve ser maior que Zero");
			}
			else if(coordY<=0) {
				throw new ErroValidacaoFiltro("Coord Y deve ser maior que Zero");
			}
			else if (dmax==null||dmax<=0) {
				throw new ErroValidacaoFiltro("dmax deve ser maior ou igual a Zero");
			}
		}
}
