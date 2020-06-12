package br.com.xy.inc.poi.model;

import java.util.List;
import java.util.stream.Collectors;

public class PoiDto {
	
	private Long id;
	private String name;
	private Integer coordX;
	private Integer coordY;
	
	public PoiDto(Poi poi) {
		this.id = poi.getId();
		this.name = poi.getName();
		this.coordX = poi.getCoordX();
		this.coordY = poi.getCoordY();
	}

	public PoiDto(String name, Integer coordX, Integer coordY) {
		this.name = name;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getCoordX() {
		return coordX;
	}

	public Integer getCoordY() {
		return coordY;
	}
	
	public static List<PoiDto> listConverter(List<Poi> pois){
		return pois.stream().map(PoiDto::new).collect(Collectors.toList());
	}

	public static PoiDto poiConverter(Poi poi){
		return new PoiDto(poi);
	}
}
