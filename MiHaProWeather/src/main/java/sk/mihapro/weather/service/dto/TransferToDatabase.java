package sk.mihapro.weather.service.dto;

import java.util.Map;

import sk.mihapro.weather.service.DatabaseUtil;
import sk.mihapro.weather.service.OpenWeatherAPIGetData;
/**
 * Class contains a method for saving of selected data in Postgres database.
 * 
 * To be done:  Saving whole json file as object in the database
 * 
 * @author MiHaPro
 *
 */

public class TransferToDatabase {
	
	DatabaseUtil dbObj = new DatabaseUtil();
	
	Map<String, Object> weatherDataMap;
	
	public TransferToDatabase(Map<String, Object> weatherDataMap) {
		this.weatherDataMap = weatherDataMap;
	}
	// In this method selected data from OpenWeatherAPI are initialized to variables
	public void saveDataInPostgres() {
		
		Map<String, Object> mainMap = OpenWeatherAPIGetData.jsonToMap(weatherDataMap.get("main").toString());
		Map<String, Object> sysMap = OpenWeatherAPIGetData.jsonToMap(weatherDataMap.get("sys").toString());
		Map<String, Object> windMap = OpenWeatherAPIGetData.jsonToMap(weatherDataMap.get("wind").toString());

			String location = weatherDataMap.get("name").toString() +", " + sysMap.get("country");
			Double temperature = (Double) mainMap.get("temp"); 
			Double pressure = (Double) mainMap.get("pressure"); 
			Double humidity = (Double) mainMap.get("humidity");
			Double windSpeed = (Double) windMap.get("speed");
			Double windAngle = (Double) windMap.get("deg");
			
			// creating table in database if does not exists
			dbObj.createTable(); 
			
			// passing selected data to the database 
			dbObj.addWeatherDataToTable(location, temperature, pressure, humidity, windSpeed, windAngle);

	}
}
