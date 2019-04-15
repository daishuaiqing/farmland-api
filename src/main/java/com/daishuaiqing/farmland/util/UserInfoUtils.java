package com.daishuaiqing.farmland.util;

import com.daishuaiqing.farmland.dto.WxUserTokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class UserInfoUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取请求头携带token
     * 使用token获取Redis中微信用户的信息
     * @return
     */
    public WxUserTokenInfo getWxUserInfo(){
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader("token");
        return (WxUserTokenInfo)redisTemplate.opsForValue().get(token);
    }

    /**
     * 将查询的微信用户的信息存入Redis
     * @param userTokenInfo
     * @return
     */
    public String setWxUserInfo(WxUserTokenInfo userTokenInfo){
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token,userTokenInfo);
        redisTemplate.expire(token,7, TimeUnit.DAYS);
        return token;
    }

}
