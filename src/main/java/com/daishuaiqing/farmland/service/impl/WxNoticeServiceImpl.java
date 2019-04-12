package com.daishuaiqing.farmland.service.impl;

import com.daishuaiqing.farmland.domain.WxNotice;
import com.daishuaiqing.farmland.dao.WxNoticeDao;
import com.daishuaiqing.farmland.service.WxNoticeService;
import com.daishuaiqing.farmland.query.WxNoticeQuery;
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
public class WxNoticeServiceImpl implements WxNoticeService {

    @Autowired
    private WxNoticeDao wxNoticeDao;

    /**
    * wxNotice 设置默认值
    * 创建时间，更新时间，是否删除
    * @param wxNotice
    */
    private void setDefaultValue(WxNotice wxNotice) {
        wxNotice.setCreateTime(LocalDateTime.now());
        wxNotice.setUpdateTime(LocalDateTime.now());
        wxNotice.setDeleted(0);
    }

    @Override
    public CommonResult findById(Long id) {
        return new CommonResult().success(wxNoticeDao.findById(id).orElse(null));
    }

    @Override
    public CommonResult findAll() {
        return new CommonResult().success(wxNoticeDao.findAll());
    }

    @Override
    public CommonResult add(WxNotice wxNotice) {
        setDefaultValue(wxNotice);
        return new CommonResult().success(wxNoticeDao.save(wxNotice));
    }

    @Override
    public CommonResult modify(WxNotice wxNotice) {
        WxNotice data = wxNoticeDao.findById(wxNotice.getId()).orElse(null);
        wxNotice.setCreateTime(data.getCreateTime());
        wxNotice.setDeleted(data.getDeleted());
        wxNotice.setUpdateTime(LocalDateTime.now());
        return new CommonResult().success(wxNoticeDao.save(wxNotice));
    }

    @Override
    public CommonResult list(Pageable pageable,WxNoticeQuery wxNoticeQuery) {
        Specification<WxNotice> specification = new Specification<WxNotice>() {
            @Override
            public Predicate toPredicate(Root<WxNotice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                /*if(StringUtils.isNotBlank(wxNoticeQuery)){
                    predicates.add(cb.like(root.get("courseName").as(String.class),"%"+wxNoticeQuery.getCourseName()+"%"));
                }
                if(wxNotice!=null){
                    predicates.add(cb.equal(root.get("state").as(Boolean.class),wxNoticeQuery.getState()));
                }*/
                if (predicates.size() == 0) {
                    return null;
                }
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);
                return cb.and(predicateArr);
            }

        };
         return new CommonResult().success(wxNoticeDao.findAll(specification,pageable));
    }

    @Override
    public CommonResult deleteById(Long id) {
        wxNoticeDao.deleteById(id);
        return new CommonResult().success(!wxNoticeDao.existsById(id));
    }


}