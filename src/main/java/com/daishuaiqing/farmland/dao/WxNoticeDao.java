package com.daishuaiqing.farmland.dao;

import com.daishuaiqing.farmland.domain.WxNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxNoticeDao extends JpaRepository<WxNotice, Long>,JpaSpecificationExecutor<WxNotice> {
 }