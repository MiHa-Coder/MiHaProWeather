package sk.mihapro.weather.service.dto;
/**
 * This class contains two methods that are returning String values of image
 * addresses.
 * 
 * To be refactored to:
 * if(weatherDescription.contains("...."){
 * 		return weatherImage;
 * }
 * 
 * @author MiHaPro
 *
 */
public class WeatherImage {

	private String weatherIcon;
	private String weatherBackgroundImage;

	private String weatherDescription;

	public WeatherImage(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	
	// This method returns String value of image icon address which 
	// is chosen according to weather description of read data from OpenWeatherAPI
	public String getWeatherIcon() {

		switch (weatherDescription) {

		case "clear sky":
			weatherIcon = "/css/images/sun_icon.png";
			break;

		case "few clouds":
			weatherIcon = "/css/images/sun_lite_clouds_icon.png";
			break;
			
		case "scattered clouds":
			weatherIcon = "/css/images/sun_mid_clouds_icon.png";
			break;
			
		case "broken clouds":
		case "overcast clouds":
		case "mist":
			weatherIcon = "/css/images/clouds_icon.png";
			break;
			
		case "light intensity drizzle":
		case "drizzle":
		case "heavy intensity drizzle":
		case "light intensity drizzle rain":
		case "drizzle rain":
		case "heavy intensity drizzle rain":
		case "shower rain and drizzle":
		case "heavy shower rain and drizzle":
		case "shower drizzle":
		case "light rain":
		case "moderate rain":
			weatherIcon = "/css/images/lite_rain_icon.png";
			break;
			
		case "rain":
		case "heavy intensity rain":
		case "very heavy rain":
		case "extreme rain":
		case "light intensity shower rain":
		case "shower rain":
		case "heavy intensity shower rain":
		case "ragged shower rain":
			weatherIcon = "/css/images/heavy_rain_icon.png";
			break;
			
		case "thunderstorm":
		case "thunderstorm with light rain":
		case "thunderstorm with rain":
		case "thunderstorm with heavy rain":
		case "light thunderstorm":
		case "heavy thunderstorm":
		case "ragged thunderstorm":
		case "thunderstorm with light drizzle":
		case "thunderstorm with drizzle":
		case "thunderstorm with heavy drizzle":
			weatherIcon = "/css/images/storm_icon.png";
			break;
			
		case "snow":
		case "light snow":
		case "Snow":
		case "Heavy snow":
		case "Sleet":
		case "Light shower sleet":
		case "Light shower snow":
		case "Shower snow":
		case "freezing rain":
			weatherIcon = "/css/images/heavy_snow_icon.png";
			break;
		}

		return weatherIcon;
	}

	// This method returns String value of background address which 
	// is chosen according to weather description of read data from OpenWeatherAPI
	public String getWeatherBackgroundImage() {

		switch (weatherDescription) {

		case "clear sky":
			weatherBackgroundImage = "/css/images/sun.jpg";
			break;
			
		case "few clouds":
		case "scattered clouds":
		case "broken clouds":
		case "overcast clouds":
		case "mist":
			weatherBackgroundImage = "/css/images/clouds.jpg";
			break;
			
		case "light intensity drizzle":
		case "drizzle":
		case "heavy intensity drizzle":
		case "light intensity drizzle rain":
		case "drizzle rain":
		case "heavy intensity drizzle rain":
		case "shower rain and drizzle":
		case "heavy shower rain and drizzle":
		case "shower drizzle":
		case "light rain":
		case "moderate rain":
		case "rain":
		case "heavy intensity rain":
		case "very heavy rain":
		case "extreme rain":
		case "light intensity shower rain":
		case "shower rain":
		case "heavy intensity shower rain":
		case "ragged shower rain":
			weatherBackgroundImage = "/css/images/rain.jpg";
			break;
			
		case "thunderstorm":
		case "thunderstorm with light rain":
		case "thunderstorm with rain":
		case "thunderstorm with heavy rain":
		case "light thunderstorm":
		case "heavy thunderstorm":
		case "ragged thunderstorm":
		case "thunderstorm with light drizzle":
		case "thunderstorm with drizzle":
		case "thunderstorm with heavy drizzle":
			weatherBackgroundImage = "/css/images/storm.jpg";
			break;
			
		case "snow":
		case "light snow":
		case "Snow":
		case "Heavy snow":
		case "Sleet":
		case "Light shower sleet":
		case "Light shower snow":
		case "Shower snow":
		case "freezing rain":

			weatherBackgroundImage = "/css/images/snow.jpg";
			break;
		}
		return weatherBackgroundImage;
	}
}
