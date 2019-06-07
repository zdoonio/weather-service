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
	private int pressure;
	private int humidity;
	private float temperatureMin;
	private float temperatureMax;
	private Date weatherDate;
	private Date createdDate;

	public Weather(String weather, double temperature, int pressure, Date weatherDate){
		this.weather = weather;
		this.temperature = temperature;
		this.pressure = pressure;
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

	public float getPressure() {
		return pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public float getTemperatureMin() {
		return temperatureMin;
	}

	public float getTemperatureMax() {
		return temperatureMax;
	}

	public Date getWeatherDate() {
		return weatherDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

}
