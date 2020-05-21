package XY.Inc.POI.controller;

import java.net.URI;
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
import XY.Inc.POI.model.PoiFiltroDto;
import XY.Inc.POI.model.PoiFormDto;
import XY.Inc.POI.repository.PoiRepository;
import XY.Inc.POI.service.Filtro;

@RestController
@RequestMapping("/pois")
public class PoiController {
	
	@Autowired
	public PoiRepository poiRepository;
	
	@GetMapping
  	public List<PoiDto> Pois(PoiFiltroDto form) {
		List<Poi> pois = poiRepository.findAll();
		
		if(form.getCoordX()==null&&form.getCoordY()==null) {
			return PoiDto.converter(pois);
		}
		else {
			Filtro filtro = new Filtro();
			List<Poi> resultado = filtro.AplicaFiltro(pois, form);
			return PoiDto.converter(resultado);
		}
		
	}
		
	@PostMapping 
	public ResponseEntity<PoiDto> cadastrar(@RequestBody@Valid PoiFormDto form, UriComponentsBuilder uriBuilder){
		Poi poi = form.converter();
		poiRepository.save(poi);
		URI uri = uriBuilder.path("/pois/{id}").buildAndExpand(poi.getId()).toUri();
		return ResponseEntity.created(uri).body(new PoiDto(poi));
	
	}
}
