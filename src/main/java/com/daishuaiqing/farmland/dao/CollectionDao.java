package com.daishuaiqing.farmland.dao;

import com.daishuaiqing.farmland.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CollectionDao extends JpaRepository<Collection, Long>,JpaSpecificationExecutor<Collection> {
 }