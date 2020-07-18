package sk.mihapro.weather.service.dto;

/**
 * This class contains a method for transformation wind angle to wind direction
 * 
 * @author MiHaPro
 *
 */
public class WindDirection {

	private String windDirection;
	private Double windAngle;

	// Constructor of the class taking Double argument of
	// wind angle in degrees from WeatherData class
	public WindDirection(Double windAngle) {
		this.windAngle = windAngle;
	}
	
	// Method returns String value of wind direction, which depends on wind angle
	public String getWindDirection() {

		if (windAngle >= 337.5 && windAngle <= 360 || windAngle >= 0 && windAngle < 22.5) {
			windDirection = "North";
		} else if (windAngle >= 22.5 && windAngle < 67.5) {
			windDirection = "North East";
		} else if (windAngle >= 67.5 && windAngle < 112.5) {
			windDirection = "East";
		} else if (windAngle >= 112.5 && windAngle < 157.5) {
			windDirection = "South East";
		} else if (windAngle >= 157.5 && windAngle < 202.5) {
			windDirection = "South";
		} else if (windAngle >= 202.5 && windAngle < 247.5) {
			windDirection = "South West";
		} else if (windAngle >= 247.5 && windAngle < 292.5) {
			windDirection = "West";
		} else if (windAngle >= 292.5 && windAngle < 337.5) {
			windDirection = "North West";
		}

		return windDirection;
	}

}
