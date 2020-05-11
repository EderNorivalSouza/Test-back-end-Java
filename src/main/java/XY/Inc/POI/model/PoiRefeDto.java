package XY.Inc.POI.model;

import javax.validation.constraints.NotNull;

public class PoiRefeDto {
	
	@NotNull
	private Integer coordX;
	@NotNull
	private Integer coordY;
	
	public Integer getCoordX() {
		return coordX;
	}
	
	public Integer getCoordY() {
		return coordY;
	}
	
	

}
