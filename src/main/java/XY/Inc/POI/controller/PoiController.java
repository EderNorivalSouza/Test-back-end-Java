package XY.Inc.POI.controller;

import XY.Inc.POI.model.Poi;
import XY.Inc.POI.model.PoiDto;
import XY.Inc.POI.model.PoiFiltroDto;
import XY.Inc.POI.model.PoiFormDto;
import XY.Inc.POI.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pois")
public class PoiController {

	@Autowired
	public Services services;

	@GetMapping
  	public List<PoiDto> Pois(PoiFiltroDto form) {
		List<Poi> pois = services.listarPois();
		
		if(services.validacao(form)==true) {
			return PoiDto.converter(pois);
		}
		else {
			List<Poi> resultado = services.AplicaFiltro(pois,form);
			return PoiDto.converter(resultado);
		}
		
	}
		
	@PostMapping 
	public ResponseEntity<PoiDto> cadastrar(@RequestBody@Valid PoiFormDto form, UriComponentsBuilder uriBuilder){
		Poi poi = form.converter();
		services.adicionaPoi(poi);
		URI uri = uriBuilder.path("/pois/{id}").buildAndExpand(poi.getId()).toUri();
		return ResponseEntity.created(uri).body(new PoiDto(poi));
	
	}
}
