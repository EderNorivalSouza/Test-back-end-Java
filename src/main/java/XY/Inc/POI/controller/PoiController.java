package XY.Inc.POI.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import XY.Inc.POI.model.Poi;
import XY.Inc.POI.model.PoiDto;
import XY.Inc.POI.model.PoiFormDto;
import XY.Inc.POI.model.PoiRefeDto;
import XY.Inc.POI.repository.PoiRepository;

@RestController
@RequestMapping("/pois")
public class PoiController {
	
	@Autowired
	public PoiRepository poiRepository;
	
	@GetMapping
  	public List<PoiDto> allPois() {
		List<Poi> pois = poiRepository.findAll();		
		return PoiDto.converter(pois);
	}
	
	@PostMapping 
	public ResponseEntity<PoiDto> cadastrar(@RequestBody@Valid PoiFormDto form, UriComponentsBuilder uriBuilder){
		Poi poi = form.converter();
		poiRepository.save(poi);
		URI uri = uriBuilder.path("/pois/{id}").buildAndExpand(poi.getId()).toUri();
		return ResponseEntity.created(uri).body(new PoiDto(poi));
	
	}
	
	
	@PostMapping("/filtro")
	public List<PoiDto> buscapois(@RequestBody@Valid PoiRefeDto form){
		List<Poi> pois = poiRepository.findAll();
		List<Poi> resultado = new ArrayList<Poi>();
		for (Poi poi:pois) {
						
			int x = Math.abs(poi.getCoordX()-form.getCoordX());
			int y = Math.abs(poi.getCoordY()-form.getCoordY());
							
			if((x+y)<=10) {
				resultado.add(poi);
				
			}			
			
		}	
		
		return PoiDto.converter(resultado);
	}

}
