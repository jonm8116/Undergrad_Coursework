package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRecordRepository extends MongoRepository<GameRecord, User> {
	
	public List<GameRecord> findByUsername(String username);
}

