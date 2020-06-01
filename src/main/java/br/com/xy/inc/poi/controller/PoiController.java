package br.com.xy.inc.poi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiDto;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.model.PoiFormDto;
import br.com.xy.inc.poi.service.PoiServices;

@RestController
@RequestMapping("/pois")
public class PoiController {

	private final PoiServices services;

	public PoiController(PoiServices services) {
		this.services = services;
	}

	@GetMapping
  	public List<PoiDto> Pois() {
		return PoiDto.converter(services.findAll());
	}
	
	@PostMapping("/filter")
	public List<PoiDto> filterPois(@RequestBody @Valid PoiFilterDto form){
		return PoiDto.converter(services.filteredPois(services.findAll(),form));
	}
		
	@PostMapping 
	public ResponseEntity<PoiDto> addPoi(@RequestBody@Valid PoiFormDto form, UriComponentsBuilder uriBuilder){
		Poi poi = form.converter();
		services.addPoi(poi);
		URI uri= uriBuilder.path("/pois/{id}").buildAndExpand(poi.getId()).toUri();
		return ResponseEntity.created(uri).body(new PoiDto(poi));
		
	}
	
	
}
