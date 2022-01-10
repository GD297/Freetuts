package com.fsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.entity.NavEntity;

@Repository
public interface NavRepository extends JpaRepository<NavEntity, String> {
	
}
