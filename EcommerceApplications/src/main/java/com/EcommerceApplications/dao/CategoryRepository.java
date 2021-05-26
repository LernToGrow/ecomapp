package com.EcommerceApplications.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EcommerceApplications.entites.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

}
