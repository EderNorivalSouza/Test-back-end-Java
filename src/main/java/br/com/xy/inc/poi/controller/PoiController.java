package br.com.xy.inc.poi.controller;

import br.com.xy.inc.poi.model.Poi;
import br.com.xy.inc.poi.model.PoiDto;
import br.com.xy.inc.poi.model.PoiFilterDto;
import br.com.xy.inc.poi.model.PoiFormDto;
import br.com.xy.inc.poi.service.PoiServices;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/pois")
public class PoiController {

	private final PoiServices services;

	public PoiController(PoiServices services) {
		this.services = services;
	}

	@GetMapping
  	public List<PoiDto> Pois() {
		return PoiDto.listConverter(services.findAll());
	}

	@GetMapping("/id")
	public PoiDto poiById(@RequestParam @Valid @Min(1) Long id){
		return PoiDto.poiConverter(services.findById(id));
	}
	
	@PostMapping("/filter")
	public List<PoiDto> filterPois(@RequestBody @Valid PoiFilterDto form){
		return PoiDto.listConverter(services.filteredPois(services.findAll(),form));
	}
		
	@PostMapping 
	public PoiDto addPoi(@RequestBody@Valid PoiFormDto form){
		Poi poi = form.converter();
		services.addPoi(poi);
//		URI uri= uriBuilder.path("/pois/{id}").buildAndExpand(poi.getId()).toUri();
		return new PoiDto(poi);
		
	}
}
