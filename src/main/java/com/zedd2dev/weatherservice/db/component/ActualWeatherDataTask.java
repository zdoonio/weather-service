package com.zedd2dev.weatherservice.db.component;

import com.zedd2dev.weatherservice.db.Weather;
import com.zedd2dev.weatherservice.db.service.WeatherService;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Dominik Zduńczyk on 21.06.19.
 */
@Component
public class ActualWeatherDataTask {
	private static final Logger log = LoggerFactory.getLogger(ActualWeatherDataTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final String appID = "9673d1b06c241189ddecc908bbc4defc";
	private static final String town = "Warsaw";

	@Autowired
	private WeatherService weatherService;

	@Scheduled(fixedRate = 5000)
	public void executeTask() throws MalformedURLException {
		log.info("Task executed at {}", dateFormat.format(new Date()));

		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + town + "&APPID=" + appID + "&units=metric");
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responseCode = conn.getResponseCode();
			if (responseCode != 200)
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			else {
				Scanner sc = new Scanner(url.openStream());

				StringBuilder inlineBuilder = new StringBuilder();
				while (sc.hasNext())
					inlineBuilder.append(sc.nextLine());
				String inline = inlineBuilder.toString();

				log.info("JSON data in string format");
				log.info(inline);

				sc.close();

				JSONParser parse = new JSONParser();
				JSONObject jsonObject = (JSONObject) parse.parse(inline);
				JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
				JSONObject mainData = (JSONObject) jsonObject.get("main");
				JSONObject weatherData = (JSONObject) weatherArray.get(0);

				Weather weather = new Weather((String) weatherData.get("main"),
						(String) weatherData.get("description"),
						(double) mainData.get("temp"),
						(long) mainData.get("pressure"),
						(long) mainData.get("humidity"),
						new Date((long) jsonObject.get("dt")));

				weatherService.save(weather);

				log.info("Weather Saved!");
			}

		} catch (IOException e) {
			log.warn("Unable to save weather: " + e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


}


