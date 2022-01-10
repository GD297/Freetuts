package com.fsoft.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.entity.BlogEntity;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, String> {
	
	List<BlogEntity> findAllByTypeOrderByCreateDateDesc(String type);
	
	List<BlogEntity> findByCreateDate(Date date);
	
	List<BlogEntity> findAll(Sort sort);
//	
//	Page

	BlogEntity findByBlogID(String id);
	
	List<BlogEntity> findAllCountAuthorAndGroupByAuthor(String author);
//	
//	@Query(value="SELECT TOP(10) author, COUNT(author) As number FROM [FreeTutsProject].[dbo].[blog] GROUP BY author ORDER BY number DESC")
//	List<BlogEntity> findAuthorCountAuthorGroupByAuthor();

	List<BlogEntity> findAllByOrderByCreateDateDesc();

	List<BlogEntity> findByCreateDateAfter(Date dateStart);

	BlogEntity findByLinkContent(String linkContent);

	
	
//	@Query(value = "SELECT * "
//			 	 + " FROM blog LEFT JOIN sub_category "
//			 	 + " ON blog.sub_cateid = sub_category.sub_cateid " 
//				 + " WHERE sub_category.title = :subCate_Title "
//				 + " ORDER BY blog.create_date DESC", nativeQuery = true)
//	List<BlogEntity> findAllCouponBySubCateTitle(@Param("subCate_Title") String subCate_Title);

//	@Query(value = "SELECT TOP (5) * "
//				+ " FROM blog "
//				+ " WHERE type = :type "
//				+ " ORDER BY create_date DESC", nativeQuery = true)
//	BlogEntity getTop5(@Param("type") String type);
}
