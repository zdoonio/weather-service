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
	private SequenceGeneratorService sequenceGeneratorService;

	public WeatherService(WeatherRepo weatherRepo, SequenceGeneratorService sequenceGeneratorService) {
		this.weatherRepo = weatherRepo;
		this.sequenceGeneratorService = sequenceGeneratorService;
	}

	public Iterable<Weather> list() {
		return weatherRepo.findAll();
	}

	public Weather save(Weather weather) {
		weather.setId(sequenceGeneratorService.generateSequence(Weather.SEQUENCE_NAME));
		return weatherRepo.save(weather);
	}

	public Iterable<Weather> save(List<Weather> weatherList) {
		List<Weather> savedList = new ArrayList<>();
		for (Weather weather : weatherList) {
			savedList.add(weatherRepo.save(weather));
		}
		return savedList;
	}
}