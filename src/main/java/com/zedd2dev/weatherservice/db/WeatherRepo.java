package com.zedd2dev.weatherservice.db;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Dominik Zduńczyk on 07.06.19.
 */
public interface WeatherRepo extends MongoRepository<Weather, String> {}

