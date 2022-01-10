package com.fsoft.dto;

import java.io.Serializable;

import com.fsoft.entity.AdminEntity;
import com.fsoft.entity.SkillsEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillsAdminDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AdminEntity admin;
	private SkillsEntity skills; 
	private Integer skillsPercent;

}
