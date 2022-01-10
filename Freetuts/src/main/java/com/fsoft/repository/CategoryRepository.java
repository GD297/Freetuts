package com.fsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.entity.CateEntity;



@Repository
public interface CategoryRepository extends JpaRepository<CateEntity, String> {

	List<CateEntity> findAllByNavID(String navID);
	
	CateEntity findByLinkCate(String linkCate);

//	@Transactional
//	@Modifying
//	@Query("SELECT cateid, SET title = :title, link_cate = :link_cate, navid = :navid WHERE cateid = :cateid")
//	List<CateEntity> findAllByNavIDLike(String navID);
	//void setCateEntityInfoByNavID (CateEntity cate);
//	
//	@Transactional
//	@Modifying
//	@Query("UPDATE CateEntity SET title = :title, link_cate = :link_cate, navid = :navid WHERE cateid = :cateid")
//	void updateCategory(@Param("title") String title, @Param("link_cate") String link_cate, @Param("navid") String navid, @Param("cateid")String cateid);
}

