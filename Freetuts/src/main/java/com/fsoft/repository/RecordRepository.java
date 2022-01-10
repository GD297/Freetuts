package com.fsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.entity.RecordEntity;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, String>{

	List<RecordEntity> findAllByOrderByTimeDesc();
	
}
