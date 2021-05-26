package com.EcommerceApplications.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.EcommerceApplications.entites.Category;
import com.EcommerceApplications.entites.Product;
import com.EcommerceApplications.entites.User;

public interface productRepository extends JpaRepository<Product,Integer>{
	
	

	
/*	@Query("from product as c where c.category.cid=:cid")
	public List<Product> findProductsByCategory(@Param("cid")int cid);*/
	
}
