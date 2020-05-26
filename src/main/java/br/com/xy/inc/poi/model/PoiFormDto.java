package br.com.xy.inc.poi.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class PoiFormDto {
	
	@NotEmpty
	private String name;
	@NotNull
	private Integer coordX;
	@NotNull
	private Integer coordY;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCoordX() {
		return coordX;
	}
	public void setCoordX(Integer coordX) {
		this.coordX = coordX;
	}
	public Integer getCoordY() {
		return coordY;
	}
	public void setCoordY(Integer coordY) {
		this.coordY = coordY;
	}
	public Poi converter() {
		
		return new Poi(name, coordX, coordY);
	}
	
	
	
}
