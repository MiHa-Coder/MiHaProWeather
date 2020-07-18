package sk.mihapro.weather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.*;

import sk.mihapro.weather.service.dto.TransferToDatabase;
/**
 * Creating connection to OpenWeatherApi.
 * Parsing json data to map
 * Storing data in postgres 
 * @author MiHaPro
 *
 */

public class OpenWeatherAPIGetData {

	// Parsing data from json to Map with using of Gson framework
	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());
		return map;
	}

	// Getting data from OpenWeatherAPI
	public Map<String, Object> weatherData() {

		Map<String, Object> respMap = null;
		
		// Parameters needed for creating connection to OpenWeatherAPI
		final String API_KEY = "f55bde5bc7618a7d28a3dae1076749a3";
		final String LOCATION = "Bratislava, SK";
		final String URL_STRING = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY
				+ "&units=metric";

		try {
			StringBuilder result = new StringBuilder();
			
			// Creating connection
			URL url = new URL(URL_STRING);
			URLConnection conn = url.openConnection();
			
			// Reading data from OpenWeatherAPI
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			// Closing connection
			rd.close();
			System.out.println(result);

			// Parsing data to map
			respMap = jsonToMap(result.toString());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// Creating object and calling the method for saving data in postgreSQL
		TransferToDatabase ttd = new TransferToDatabase(respMap);
		ttd.saveDataInPostgres();
		
		return respMap;
	}
	
}
