package com.zedd2dev.weatherservice.db;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Dominik Zduńczyk on 07.06.19.
 */
@Document(collection = "weather")
public class Weather {

	@Transient
	public static final String SEQUENCE_NAME = "weather_sequence";

	@Id
	private long id;
	private String weather;
	private String weatherDescription;
	private double temperature;
	private long pressure;
	private long humidity;
	private Date weatherDate;
	private Date createdDate;

	public Weather(String weather, String weatherDescription, double temperature, long pressure, long humidity, Date weatherDate){
		this.weather = weather;
		this.weatherDescription = weatherDescription;
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		this.weatherDate = weatherDate;
		this.createdDate = new Date();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getWeather() {
		return weather;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public double getTemperature() {
		return temperature;
	}

	public long getPressure() {
		return pressure;
	}

	public long getHumidity() {
		return humidity;
	}

	public Date getWeatherDate() {
		return weatherDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

}
