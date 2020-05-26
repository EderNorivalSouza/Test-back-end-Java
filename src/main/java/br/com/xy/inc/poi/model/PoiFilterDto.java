package br.com.xy.inc.poi.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PoiFilterDto {
	
	@NotNull@Min(value = 0)
	Integer dmax;
	@NotNull@Min(value = 1)
	Integer coordX;
	@NotNull@Min(value = 1)
	Integer coordY;
	
	
	public PoiFilterDto(Integer coordX, Integer coordY, Integer dmax) {
		this.dmax = dmax;
		this.coordX = coordX;
		this.coordY = coordY;
	}


	public Integer getDmax() {
		return dmax;
	}


	public Integer getCoordX() {
		return coordX;
	}


	public Integer getCoordY() {
		return coordY;
	}
	
	

}
