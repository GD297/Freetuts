package com.fsoft.service;

import java.util.List;

import com.fsoft.dto.RecordDTO;

import reactor.core.publisher.Flux;

public interface RecordService {

	List<RecordDTO> loadNewestRecord();
	
	void recordUpdate(String blogID, String author, String message, String status);

	Flux<RecordDTO> loadAllRecord();
	
}
