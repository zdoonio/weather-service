package com.zedd2dev.weatherservice.controller;

import com.zedd2dev.weatherservice.db.Weather;
import com.zedd2dev.weatherservice.db.WeatherRepo;
import com.zedd2dev.weatherservice.db.service.SequenceGeneratorService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dominik Zduńczyk on 07.06.19.
 */
@RestController
public class WeatherController {

	private WeatherRepo weatherRepo;

	private SequenceGeneratorService sequenceGenerator;

	@Autowired
	WeatherController(WeatherRepo weatherRepo, SequenceGeneratorService sequenceGenerator) {
		this.weatherRepo = weatherRepo;
		this.sequenceGenerator = sequenceGenerator;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		weatherRepo.deleteAll();

		Weather weather1 = new Weather("Cloudy", 3, 1014, new Date());
		weather1.setId(sequenceGenerator.generateSequence(Weather.SEQUENCE_NAME));
		weatherRepo.save(weather1);

		Weather weather2 = new Weather("Sunny", 24, 1014, new Date());
		weather2.setId(sequenceGenerator.generateSequence(Weather.SEQUENCE_NAME));
		weatherRepo.save(weather2);

		Weather weather3 = new Weather("Windy", 11, 1014, new Date());
		weather3.setId(sequenceGenerator.generateSequence(Weather.SEQUENCE_NAME));
		weatherRepo.save(weather3);

		Weather weather4 = new Weather("Snow", -3, 1014, new Date());
		weather4.setId(sequenceGenerator.generateSequence(Weather.SEQUENCE_NAME));
		weatherRepo.save(weather4);

	}

	@GetMapping("/weather")
	public List<Weather> getAll() {
		return weatherRepo.findAll();
	}
}
