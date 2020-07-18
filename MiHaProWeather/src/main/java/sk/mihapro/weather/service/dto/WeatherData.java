package sk.mihapro.weather.service.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;

import sk.mihapro.weather.service.OpenWeatherAPIGetData;

/**
 * This class takes data from OpenWeatherApi and contains methods that
 * return selected values which are passed to the front-end
 * @author MiHaPro
 *
 */
public class WeatherData {

	private Map<String, Object> dataFromOpenWeather;

	public WeatherData(Map<String, Object> dataFromOpenWeather) {
		this.dataFromOpenWeather = dataFromOpenWeather;
	}
	private String location;
	private Double temperature;
	private Double feelsLikeTemperature;
	private Double pressure;
	private Double humidity;
	private Double visibility;
	private String windSpeed;
	private Double windAngle;
	private String weatherDescription;
	private String sunrise;
	private String sunset;

	// city
	public String getLocation() {
		location = dataFromOpenWeather.get("name").toString();
		return location;
	}

	// in deg C
	public Double getTemperature() {
		Map<String, Object> mainMap = OpenWeatherAPIGetData.jsonToMap(dataFromOpenWeather.get("main").toString());
		temperature = (Double) mainMap.get("temp");
		return temperature;
	}

	// in deg C
	public Double getFeelsLikeTemperature() {
		Map<String, Object> mainMap = OpenWeatherAPIGetData.jsonToMap(dataFromOpenWeather.get("main").toString());
		feelsLikeTemperature = (Double) mainMap.get("feels_like");
		return feelsLikeTemperature;
	}

	// in hPa
	public Double getPressure() {
		Map<String, Object> mainMap = OpenWeatherAPIGetData.jsonToMap(dataFromOpenWeather.get("main").toString());
		pressure = (Double) mainMap.get("pressure");
		return pressure;
	}

	// in %
	public Double getHumidity() {
		Map<String, Object> mainMap = OpenWeatherAPIGetData.jsonToMap(dataFromOpenWeather.get("main").toString());
		humidity = (Double) mainMap.get("humidity");
		return humidity;
	}

	// in m
	public Double getVisibility() {
		visibility = (Double) dataFromOpenWeather.get("visibility");
		return visibility;
	}

	// in km/h
	public String getWindSpeed() {
		Map<String, Object> windMap = OpenWeatherAPIGetData.jsonToMap(dataFromOpenWeather.get("wind").toString());
		windSpeed = String.format("%.2f", (((Double) windMap.get("speed")) * 3600) / 1000);
		return windSpeed;
	}

	// in degrees
	public Double getWindAngle() {
		Map<String, Object> windMap = OpenWeatherAPIGetData.jsonToMap(dataFromOpenWeather.get("wind").toString());
		windAngle = (Double) windMap.get("deg");
		return windAngle;
	}

	// weather description
	@SuppressWarnings("unchecked")
	public String getWeatherDescription() {
		ArrayList<Object> weatherArrayList = (ArrayList<Object>) dataFromOpenWeather.get("weather"); // refactor wDes
		LinkedTreeMap<String, Object> weatherMap = (LinkedTreeMap<String, Object>) weatherArrayList.get(0);
		weatherDescription = weatherMap.get("description").toString();
		return weatherDescription;
	}

	// time of sunrise
	public String getSunrise() {
		Map<String, Object> mainMap = OpenWeatherAPIGetData.jsonToMap(dataFromOpenWeather.get("sys").toString());
		Long sr = Long.parseLong(String.format("%.0f", (Double) mainMap.get("sunrise")));
		Date time = new Date(sr * 1000);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		sunrise = timeFormat.format(time);
		return sunrise;
	}

	//time of sunset
	public String getSunset() {
		Map<String, Object> mainMap = OpenWeatherAPIGetData.jsonToMap(dataFromOpenWeather.get("sys").toString());
		Long ss = Long.parseLong(String.format("%.0f", (Double) mainMap.get("sunset")));
		Date time = new Date(ss * 1000);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		sunset = timeFormat.format(time);
		return sunset;
	}

}
