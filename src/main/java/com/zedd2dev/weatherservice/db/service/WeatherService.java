package com.zedd2dev.weatherservice.db.service;

import com.zedd2dev.weatherservice.db.Weather;
import com.zedd2dev.weatherservice.db.WeatherRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by Dominik Zduńczyk on 21.06.19.
 */
@Service
public class WeatherService {
	private WeatherRepo weatherRepo;

	public WeatherService(WeatherRepo weatherRepo) {
		this.weatherRepo = weatherRepo;
	}

	public Iterable<Weather> list() {
		return weatherRepo.findAll();
	}

	public Iterable<Weather> save(List<Weather> weatherList) {
		List<Weather> savedList = new ArrayList<>();
		for (Weather weather : weatherList) {
			savedList.add(weatherRepo.save(weather));
		}
		return savedList;
	}
}