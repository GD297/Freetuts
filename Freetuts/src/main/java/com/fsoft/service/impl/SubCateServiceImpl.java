package com.fsoft.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.dto.SubCateDTO;
import com.fsoft.entity.SubCateEntity;
import com.fsoft.repository.SubCateRepository;
import com.fsoft.service.RecordService;
import com.fsoft.service.SubCateService;


@Service
public class SubCateServiceImpl implements SubCateService{


	@Autowired
	private SubCateRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RecordService recordSevice;
	
	@Override
	public List<SubCateDTO> findAllSubCate() {	
		List<SubCateEntity> listSubCate = repository.findAll();
		List<SubCateDTO> listSubCateDto = modelMapper.map(listSubCate, new TypeToken<List<SubCateDTO>>() {}.getType());
		return listSubCateDto;
	}
	
	@Override
	public void addSubCate(SubCateDTO dto, String author) {
		dto.setSubCateID(UUID.randomUUID().toString());
		SubCateEntity entity = modelMapper.map(dto, SubCateEntity.class);
		try {

			repository.save(entity);
			recordSevice.recordUpdate(dto.getSubCateID(), author, " add new sub-category",
					"Completed");
		} catch (Exception ex) {
			recordSevice.recordUpdate(dto.getSubCateID(), author, " add new sub-category",
					"Failed");
		}
	}

	@Override
	public void updateSubCate(SubCateDTO dto, String author) {
//		String title = dto.getTitle();
//		String linkSubCate = dto.getLinkSubCate();
//		String cateID = dto.getCateID();
//		String subCateID = dto.getSubCateID();
		
		SubCateEntity entity = modelMapper.map(dto, SubCateEntity.class);
		try {

			repository.save(entity);	
			recordSevice.recordUpdate(dto.getSubCateID(), author, " update sub-category",
					"Completed");
		} catch (Exception ex) {
			recordSevice.recordUpdate(dto.getSubCateID(), author, " update sub-category",
					"Failed");
		}
	}
	
	@Override
	public boolean deleteSubCate(String id, String author) {
		try {
			
			repository.deleteById(id);
			recordSevice.recordUpdate(id, author, " delete sub-category",
					"Completed");
		} catch (IllegalArgumentException e) {
			recordSevice.recordUpdate(id, author, " delete sub-category",
					"Failed");
			return false;
		}
		 return true;
	}

	@Override
	public SubCateDTO findSubCateByID(String id) {
		SubCateEntity entity = repository.findById(id).get();
		SubCateDTO dto = modelMapper.map(entity, SubCateDTO.class );
		return dto;
	}

	@Override
	public List<SubCateDTO> listSub() {
		List<SubCateEntity> listSubCate = repository.findAll();
		List<SubCateDTO> listSubDTO = modelMapper.map(listSubCate, new TypeToken<List<SubCateDTO>>() {}.getType());
		return listSubDTO;
	}

	@Override
	public SubCateDTO getSubCateByID(String id) {
		SubCateDTO subCateDTO = null;
		try {
			SubCateEntity entity = repository.findById(id).get();
			subCateDTO = modelMapper.map(entity, SubCateDTO.class);
		} catch (Exception e) {
			return null;
		}
		return subCateDTO;
	}
}
