package com.zedd2dev.weatherservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zedd2dev.weatherservice.db.Weather;
import com.zedd2dev.weatherservice.db.WeatherRepo;
import com.zedd2dev.weatherservice.db.service.SequenceGeneratorService;
import com.zedd2dev.weatherservice.db.service.WeatherService;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherServiceApplication {

	private static final String appID = "9673d1b06c241189ddecc908bbc4defc";
	private static final String town = "Warsaw";


	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(WeatherRepo weatherRepo, SequenceGeneratorService sequenceGeneratorService) {
		return args -> {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + town + "&APPID=" + appID + "&units=metric");
			try {
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				int responseCode = conn.getResponseCode();
				if(responseCode != 200)
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				else {
					Scanner sc = new Scanner(url.openStream());
					String inline = "";

					while(sc.hasNext()) {

						inline += sc.nextLine();

					}
					System.out.println("\nJSON data in string format");
					System.out.println(inline);

					sc.close();

					JSONParser parse = new JSONParser();
					JSONObject jsonObject = (JSONObject)parse.parse(inline);
					JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
					JSONObject mainData = (JSONObject) jsonObject.get("main");
					JSONObject weatherData = (JSONObject)weatherArray.get(0);

					Weather weather = new Weather((String)weatherData.get("main"),
							(String)weatherData.get("description"),
							(double)mainData.get("temp"),
							(long)mainData.get("pressure"),
							(long)mainData.get("humidity"),
							new Date((long)jsonObject.get("dt")));
					weather.setId(sequenceGeneratorService.generateSequence(Weather.SEQUENCE_NAME));
					weatherRepo.save(weather);

					System.out.println("Weather Saved!");
				}

			} catch (IOException e){
				System.out.println("Unable to save weather: " + e.getMessage());
			}
		};
	}

}
