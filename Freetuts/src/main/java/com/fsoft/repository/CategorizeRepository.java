package com.fsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.entity.CategorizeEntity;



@Repository
public interface CategorizeRepository extends JpaRepository<CategorizeEntity, String> {

//
//	CategorizeEntity findByLinkType(String link);

}
