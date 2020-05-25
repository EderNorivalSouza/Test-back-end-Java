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
import XY.Inc.POI.model.PoiFormDto;
import XY.Inc.POI.service.Services;

@RestController
@RequestMapping("/pois")
public class PoiController {

	@Autowired
	public Services services;

	@GetMapping
  	public List<PoiDto> Pois(
  			Integer coordX,
  			Integer coordY,
  			Integer dmax) {
		return PoiDto.converter(services.listarPois(coordX, coordY, dmax));		
	}
		
	@PostMapping 
	public ResponseEntity<PoiDto> cadastrar(@RequestBody@Valid PoiFormDto form, UriComponentsBuilder uriBuilder){
		Poi poi = form.converter();
		services.adicionaPoi(poi);
		URI uri = uriBuilder.path("/pois/{id}").buildAndExpand(poi.getId()).toUri();
		return ResponseEntity.created(uri).body(new PoiDto(poi));
	
	}
}
