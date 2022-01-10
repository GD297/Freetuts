package com.fsoft.service.impl;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.dto.RecordDTO;
import com.fsoft.entity.RecordEntity;
import com.fsoft.repository.RecordRepository;
import com.fsoft.service.RecordService;

import reactor.core.publisher.Flux;

@Service
public class RecordServiceImpl implements RecordService{
	
	@Autowired
	private RecordRepository recordRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<RecordDTO> loadNewestRecord() {
		List<RecordEntity> listRecord = recordRepo.findAllByOrderByTimeDesc();
		List<RecordDTO> listRecordDTO = modelMapper.map(listRecord, new TypeToken<List<RecordDTO>>() {}.getType());
		

		return listRecordDTO;
	}

	@Override
	public void recordUpdate(String blogID, String author, String message, String status) {
		RecordDTO record = new RecordDTO();
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());  
		record.setAuthor(author);
		record.setMessage(author+message);
		record.setNotiID(UUID.randomUUID().toString());
		record.setTime(time);
		record.setStatus(status);
		RecordEntity entity = modelMapper.map(record, RecordEntity.class);
		recordRepo.save(entity);
		return;
	}

	@Override
	public  Flux<RecordDTO> loadAllRecord(){
		return Flux.interval(Duration.ofSeconds(2))
                .onBackpressureDrop()
                .map(interval -> loadNewestRecord())
                .flatMapIterable(v -> v);
	}
	

}
