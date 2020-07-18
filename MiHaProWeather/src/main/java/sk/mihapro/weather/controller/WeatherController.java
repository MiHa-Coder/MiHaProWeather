package sk.mihapro.weather.controller;
/**
 * 
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sk.mihapro.weather.service.OpenWeatherAPIGetData;
import sk.mihapro.weather.service.dto.WeatherImage;
import sk.mihapro.weather.service.dto.WindDirection;
import sk.mihapro.weather.service.dto.WeatherData;
/**
 * Controller class for MiHaPro Weather webapp 
 * Thymeleaf framework used for passing values from back-end to front-end 
 * @author MiHaPro
 *
 */
@Controller
public class WeatherController {
	
	// Mapping thymeleafValues method with index.html document
	@GetMapping(name = "/index")
	public String thymeleafValues(@RequestParam(value = "city_name", defaultValue = "", required = true) String city_name, Model model) {
		
		OpenWeatherAPIGetData weather = new OpenWeatherAPIGetData();
		WeatherData weatherData = new WeatherData(weather.weatherData());
		WeatherImage weatherImage = new WeatherImage(weatherData.getWeatherDescription());
		WindDirection windDirectionObj = new WindDirection(weatherData.getWindAngle());
		
		// Initializing selected values of data to String variables
		city_name = weatherData.getLocation();
		String temperature = weatherData.getTemperature().toString();
		String feelsLikeTemperature = weatherData.getFeelsLikeTemperature().toString();
		String pressure = weatherData.getPressure().toString();
		String humidity = weatherData.getHumidity().toString();
		String visibility = weatherData.getVisibility().toString();
		String windSpeed = weatherData.getWindSpeed();
		String getWeatherIcon = weatherImage.getWeatherIcon();
		String getWeatherBackgroundImage = weatherImage.getWeatherBackgroundImage();
		String sunrise = weatherData.getSunrise();
		String sunset = weatherData.getSunset();
		String windDirection = windDirectionObj.getWindDirection();
		
		// Adding String values to model which is used by thymeleaf
		model.addAttribute("city_name", city_name);
		model.addAttribute("temperature", temperature);
		model.addAttribute("feels_like", feelsLikeTemperature);
		model.addAttribute("pressure", pressure);
		model.addAttribute("humidity", humidity);
		model.addAttribute("visibility", visibility);
		model.addAttribute("wind_speed", windSpeed);
		model.addAttribute("wind_direction", windDirection);
		model.addAttribute("weather_icon", getWeatherIcon);
		model.addAttribute("background_image", getWeatherBackgroundImage);
		model.addAttribute("sunrise", sunrise);
		model.addAttribute("sunset", sunset);
		return "index";
	}

}
