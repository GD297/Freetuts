package com.fsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.entity.SubCateEntity;

@Repository
public interface SubCateRepository extends JpaRepository<SubCateEntity, String>{
//	@Transactional
//	@Modifying
//	@Query("UPDATE SubCateEntity SET title = :title, linkSubCate = :linkSubCate, cateID = :cateID WHERE subCateID = :subCateID")
//	void updateCategory(@Param("title") String title, @Param("linkSubCate") String linkSubCate, @Param("cateID") String cateID, @Param("subCateID")String subCateID);

}
