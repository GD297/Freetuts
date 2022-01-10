package com.fsoft.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fsoft.entity.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String>{
	
	AdminEntity findByUsername(String username);
	
	@Query("UPDATE AdminEntity a SET a.failedAttempt = ?1 WHERE a.username = ?2")
	@Modifying
    void setFailedAttemptsByUsername(int failAttempts, String username);

	@Query("UPDATE AdminEntity SET first_name = ?1, last_name = ?2,  mail = ?3,  phone = ?4,  bio = ?5  WHERE username = ?6")
	@Modifying
	@Transactional
    void setUpdateProfile(String firstname, String lastname, String mail, long phone, String bio, String username);
}