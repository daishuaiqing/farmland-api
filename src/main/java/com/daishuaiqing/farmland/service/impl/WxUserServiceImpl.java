package com.daishuaiqing.farmland.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.daishuaiqing.farmland.domain.WxUser;
import com.daishuaiqing.farmland.dao.WxUserDao;
import com.daishuaiqing.farmland.dto.WxLoginInfo;
import com.daishuaiqing.farmland.dto.WxUserTokenInfo;
import com.daishuaiqing.farmland.service.WxUserService;
import com.daishuaiqing.farmland.query.WxUserQuery;
import com.daishuaiqing.farmland.util.UserInfoUtils;
import com.daishuaiqing.farmland.vo.CommonResult;
import com.daishuaiqing.farmland.vo.WxUserResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao;
    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private UserInfoUtils userInfoUtils;

    /**
    * wxUser 设置默认值
    * 创建时间，更新时间，是否删除
    * @param wxUser
    */
    private void setDefaultValue(WxUser wxUser) {
        wxUser.setCreateTime(LocalDateTime.now());
        wxUser.setUpdateTime(LocalDateTime.now());
        wxUser.setDeleted(0);
    }

    @Override
    public CommonResult findById(Long id) {
        return new CommonResult().success(wxUserDao.findById(id).orElse(null));
    }

    @Override
    public CommonResult findAll() {
        return new CommonResult().success(wxUserDao.findAll());
    }

    @Override
    public CommonResult add(WxUser wxUser) {
        setDefaultValue(wxUser);
        return new CommonResult().success(wxUserDao.save(wxUser));
    }

    @Override
    public CommonResult modify(WxUser wxUser) {
        WxUser data = wxUserDao.findById(wxUser.getId()).orElse(null);
        wxUser.setCreateTime(data.getCreateTime());
        wxUser.setDeleted(data.getDeleted());
        wxUser.setUpdateTime(LocalDateTime.now());
        return new CommonResult().success(wxUserDao.save(wxUser));
    }

    @Override
    public CommonResult list(Pageable pageable,WxUserQuery wxUserQuery) {
        Specification<WxUser> specification = new Specification<WxUser>() {
            @Override
            public Predicate toPredicate(Root<WxUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                /*if(StringUtils.isNotBlank(wxUserQuery)){
                    predicates.add(cb.like(root.get("courseName").as(String.class),"%"+wxUserQuery.getCourseName()+"%"));
                }
                if(wxUser!=null){
                    predicates.add(cb.equal(root.get("state").as(Boolean.class),wxUserQuery.getState()));
                }*/
                if (predicates.size() == 0) {
                    return null;
                }
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);
                return cb.and(predicateArr);
            }

        };
         return new CommonResult().success(wxUserDao.findAll(specification,pageable));
    }

    @Override
    public CommonResult deleteById(Long id) {
        wxUserDao.deleteById(id);
        return new CommonResult().success(!wxUserDao.existsById(id));
    }

    /**
     * 微信用户登录
     * 逻辑：微信用户首次进入小程序-----需要提供code，和用户的信息进行登录
     * 微信用户登陆成功之后会获得一个token令牌，如果用户在7天内没用登陆过小程序，token令牌将会失效；
     * 用户打开小程序的时候会校验token令牌的有效性，如果用户的token令牌失效，将会重新登录；如果有效。将会刷新用户的token失效；
     * @param wxLoginInfo
     * @return
     */
    @Override
    public WxUserResult userLogin(WxLoginInfo wxLoginInfo) throws WxErrorException {
        WxMaJscode2SessionResult wxMaJscode2SessionResult = wxMaService.jsCode2SessionInfo(wxLoginInfo.getCode());
        String openid = wxMaJscode2SessionResult.getOpenid();
        WxUser wxUser = wxUserDao.findByOpenid(openid);
        if(ObjectUtils.isEmpty(wxUser)){
            //不存在该用户，创建该用户
            wxUser = new WxUser();
            setDefaultValue(wxUser);
            BeanUtils.copyProperties(wxLoginInfo.getUserInfo(),wxUser);
            wxUser.setTelephone("");
            wxUser.setOpenid(openid);
            wxUser.setUnionid(wxMaJscode2SessionResult.getUnionid() == null ? "": wxMaJscode2SessionResult.getUnionid());
            wxUser.setSessionKey(wxMaJscode2SessionResult.getSessionKey());
            wxUser.setIsUploader(0);
            wxUserDao.save(wxUser);
        }else{
            //如果存在这个用户，更新该用户的SessionKey,以及更新时间
            wxUser.setSessionKey(wxMaJscode2SessionResult.getSessionKey());
            wxUser.setUpdateTime(LocalDateTime.now());
            wxUserDao.save(wxUser);
        }
        WxUserResult wxUserResult = new WxUserResult();
        wxUserResult.setAvatar(wxUser.getAvatar());
        wxUserResult.setNick(wxUser.getNick());
        wxUserResult.setGender(genderToString(wxUser.getGender()));
        wxUserResult.setUploader(wxUser.getIsUploader());
        WxUserTokenInfo wxUserTokenInfo = new WxUserTokenInfo();
        wxUserTokenInfo.setNick(wxUser.getNick());
        wxUserTokenInfo.setGender(wxUser.getGender());
        wxUserTokenInfo.setOpenid(wxUser.getOpenid());
        wxUserTokenInfo.setTelephone(wxUser.getTelephone());
        wxUserTokenInfo.setWxUserId(wxUser.getId());
        wxUserResult.setToken(userInfoUtils.setWxUserInfo(wxUserTokenInfo));
        return wxUserResult;
    }

    /**
     *  Integer 性别转换成中文字符串
     * @param gender
     * @return
     */
    private String genderToString(Integer gender) {
        switch (gender){
            case 0:
                return "未知";
            case 1:
                return "男";
            case 2:
                return "女";
            default:
                return "未知";
        }
    }

    /**
     * 校验微信用户token的有效性
     * 有效：刷新token的有效期，返回true
     * 失效：返回 false,让用户重新登录
     * @param token
     * @return
     */
    @Override
    public Boolean tokenCheck(String token) {
        WxUserTokenInfo wxUserTokenInfo = userInfoUtils.getWxUserInfo();
        if(ObjectUtils.isEmpty(wxUserTokenInfo)){
            //不存在则说明失效
            return false;
        }else{
            //刷新token有效期
            userInfoUtils.flushToken(token);
            return true;
        }
    }


}