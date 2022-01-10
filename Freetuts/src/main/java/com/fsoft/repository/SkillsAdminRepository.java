package com.fsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.entity.SkillsAdmin;

@Repository
public interface SkillsAdminRepository extends JpaRepository<SkillsAdmin, String> {
	
	List<SkillsAdmin> findAllByAdminUsername(String username);
}
