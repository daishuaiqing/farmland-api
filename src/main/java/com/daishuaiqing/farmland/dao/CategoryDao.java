package com.daishuaiqing.farmland.dao;

import com.daishuaiqing.farmland.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryDao extends JpaRepository<Category, Long>,JpaSpecificationExecutor<Category> {
 }