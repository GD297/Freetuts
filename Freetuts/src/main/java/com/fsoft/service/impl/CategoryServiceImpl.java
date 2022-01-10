
package com.fsoft.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.dto.CateDTO;
import com.fsoft.entity.CateEntity;
import com.fsoft.repository.CategoryRepository;
import com.fsoft.service.CategoryService;
import com.fsoft.service.RecordService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository cateRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RecordService recordSevice;

	@Override
	public List<CateDTO> getAllCate(String navID) {
		List<CateEntity> listCate = cateRepository.findAllByNavID(navID);
		List<CateDTO> listCateDTO = modelMapper.map(listCate, new TypeToken<List<CateDTO>>() {
		}.getType());
		return listCateDTO;
	}

	@Override
	public List<CateDTO> getAllCateByNavID(String navID) {
		List<CateEntity> listEntity = cateRepository.findAllByNavID(navID);
		List<CateDTO> listCateDTO = modelMapper.map(listEntity, new TypeToken<List<CateDTO>>() {
		}.getType());
		return listCateDTO;
	}

	@Override
	public String addCategory(CateDTO cateDto, String author) {
		try {
			// Check cateExits
			CateEntity cateExits = cateRepository.findByLinkCate(cateDto.getLinkCate());
			if (cateExits == null) {
				cateDto.setCateID(UUID.randomUUID().toString());
				CateEntity cateEnti = modelMapper.map(cateDto, CateEntity.class);

				cateRepository.save(cateEnti);
				recordSevice.recordUpdate(cateDto.getCateID(), author, " add new category", "Completed");
				return "SUCCESS";
			} else {
				recordSevice.recordUpdate(cateDto.getCateID(), author, " add new category", "Failed");
				return "DUPLICATE_LINK_CATE";
			} // End if cate already in DB
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			recordSevice.recordUpdate(cateDto.getCateID(), author, " add new category", "Failed");
			return "FAILED";
		}
	}

	@Override
	public String updateCategory(CateDTO cateDto, String author) {
		try {
			// Check cateExits
			CateEntity cateExits = cateRepository.findByLinkCate(cateDto.getLinkCate());
			if (cateExits == null) {
				CateEntity cateEntity = modelMapper.map(cateDto, CateEntity.class);
				cateRepository.save(cateEntity);
				recordSevice.recordUpdate(cateDto.getCateID(), author, " update category", "Completed");
				return "SUCCESS";
			} else {
				recordSevice.recordUpdate(cateDto.getCateID(), author, " update new category", "Failed");
				return "DUPLICATE_LINK_CATE";
			} // End if cate already in DB
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Something went wrong, rollback transactions");
			recordSevice.recordUpdate(cateDto.getCateID(), author, " update category", "Failed");
			return "FAILED";
		}
	}

	@Override
	public boolean deleteCategory(String id, String author) {
		try {
			cateRepository.deleteById(id);
			recordSevice.recordUpdate(id, author, " delete category", "Completed");
		} catch (Exception ex) {
			recordSevice.recordUpdate(id, author, " delete category", "Failed");
			return false;
		}
		return true;
	}

	@Override
	public List<CateDTO> findAllCate() {
		List<CateEntity> listCate = cateRepository.findAll();
		// Convert Entity to DTO Object
		List<CateDTO> listCateDTO = modelMapper.map(listCate, new TypeToken<List<CateDTO>>() {
		}.getType());
		return listCateDTO;
	}

	@Override
	public CateDTO findCateByID(String id) {
		CateEntity entity = cateRepository.findById(id).get();
		CateDTO cateDTO = modelMapper.map(entity, CateDTO.class);
		return cateDTO;
	}

//	@Override
//	public List<CateDTO> findAllByNavIDLike(String navID) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public CateDTO getCateByCateID(String id) {
		CateDTO cateDTO = null;
		try {
			CateEntity entity = cateRepository.findById(id).get();
			cateDTO = modelMapper.map(entity, CateDTO.class);
		} catch (Exception e) {
			return null;
		}
		return cateDTO;
	}
}
