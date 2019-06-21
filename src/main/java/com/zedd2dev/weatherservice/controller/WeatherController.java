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

	@GetMapping("/weather")
	public List<Weather> getAll() {
		return weatherRepo.findAll();
	}
}
