package com.daishuaiqing.farmland.service.impl;

import com.daishuaiqing.farmland.domain.WxUser;
import com.daishuaiqing.farmland.dao.WxUserDao;
import com.daishuaiqing.farmland.service.WxUserService;
import com.daishuaiqing.farmland.query.WxUserQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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


}