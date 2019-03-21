package com.daishuaiqing.farmland.util;/*
package com.daishuaiqing.template.utils;

import com.emojiet.gms.model.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class UserInfoUtils {
    @Autowired
    RedisTemplate redisTemplate;

    public AdminInfo getAdminInfo(){
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader("token");
        return (AdminInfo)redisTemplate.opsForValue().get(token);
    }
    public AdminInfo getAdminInfo(String token){
        return (AdminInfo)redisTemplate.opsForValue().get(token);
    }
    public String setAdminInfo(AdminInfo adminInfo){
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token,adminInfo);
        redisTemplate.expire(token,7, TimeUnit.DAYS);
        return token;
    }
    */
/*public MsShopAdmin getShopAdmin(){
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader("token");
        return (MsShopAdmin)redisTemplate.opsForValue().get(token);
    }

    public void setAppUser(String key,MsAppUser msAppUser){
        redisTemplate.opsForValue().set(key,msAppUser);
        redisTemplate.expire(key,30, TimeUnit.DAYS);
    }

    public MsAppUser getAppUser(){
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader("token");
        return (MsAppUser)redisTemplate.opsForValue().get(token);
    }

    public Object getAppUserByKey(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean deleteUserByKey(String key){
        return redisTemplate.delete(key);
    }*//*

}
*/
