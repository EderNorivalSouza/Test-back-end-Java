package XY.Inc.POI.controllerDto;

import javax.validation.constraints.NotNull;

public class PoiRefe {
	
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
