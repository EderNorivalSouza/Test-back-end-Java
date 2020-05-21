package XY.Inc.POI.service;

import java.util.ArrayList;
import java.util.List;

import XY.Inc.POI.model.Poi;
import XY.Inc.POI.model.PoiFiltroDto;

public class Filtro {

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
}
