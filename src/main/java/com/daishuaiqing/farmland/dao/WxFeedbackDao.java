package com.daishuaiqing.farmland.dao;

import com.daishuaiqing.farmland.domain.WxFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxFeedbackDao extends JpaRepository<WxFeedback, Long>,JpaSpecificationExecutor<WxFeedback> {
 }