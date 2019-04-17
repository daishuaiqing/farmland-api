package com.daishuaiqing.farmland.dao;

import com.daishuaiqing.farmland.domain.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxUserDao extends JpaRepository<WxUser, Long>,JpaSpecificationExecutor<WxUser> {
    WxUser findByOpenid(String openid);
}