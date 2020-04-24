package XY.Inc.POI.controllerDto;

import java.util.List;
import java.util.stream.Collectors;

import XY.Inc.POI.model.Poi;

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
	
	public static List<PoiDto> converter(List<Poi> pois){
		return pois.stream().map(PoiDto::new).collect(Collectors.toList());
	}
	
}
