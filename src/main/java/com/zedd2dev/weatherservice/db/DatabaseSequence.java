package com.zedd2dev.weatherservice.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Dominik Zduńczyk on 07.06.19.
 */
@Document(collection = "database_sequences")
public class DatabaseSequence {

	@Id
	private String id;
	private long seq;

	public String getId() {
		return id;
	}

	public long getSeq() {
		return seq;
	}
}
