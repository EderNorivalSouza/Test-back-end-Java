package br.com.xy.inc.poi.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class PoiFormDto {
	
	@NotEmpty
	private String name;
	@NotNull@Min(value = 1)
	private Integer coordX;
	@NotNull@Min(value = 1)
	private Integer coordY;

	public PoiFormDto(@NotEmpty String name, @NotNull Integer coordX, @NotNull Integer coordY) {
		this.name = name;
		this.coordX = coordX;
		this.coordY = coordY;
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
	public Poi converter() {
		return new Poi(name, coordX, coordY);
	}
	
	
	
}
