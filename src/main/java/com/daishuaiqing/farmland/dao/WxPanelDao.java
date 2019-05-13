package com.daishuaiqing.farmland.dao;

import com.daishuaiqing.farmland.domain.WxPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxPanelDao extends JpaRepository<WxPanel, Long>,JpaSpecificationExecutor<WxPanel> {
    WxPanel findByWxUid(Long id);
}