package com.daishuaiqing.farmland.service.impl;

import com.daishuaiqing.farmland.domain.WxFeedback;
import com.daishuaiqing.farmland.dao.WxFeedbackDao;
import com.daishuaiqing.farmland.service.WxFeedbackService;
import com.daishuaiqing.farmland.query.WxFeedbackQuery;
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
public class WxFeedbackServiceImpl implements WxFeedbackService {

    @Autowired
    private WxFeedbackDao wxFeedbackDao;

    /**
    * wxFeedback 设置默认值
    * 创建时间，更新时间，是否删除
    * @param wxFeedback
    */
    private void setDefaultValue(WxFeedback wxFeedback) {
        wxFeedback.setCreateTime(LocalDateTime.now());
        wxFeedback.setUpdateTime(LocalDateTime.now());
        wxFeedback.setDeleted(0);
    }

    @Override
    public CommonResult findById(Long id) {
        return new CommonResult().success(wxFeedbackDao.findById(id).orElse(null));
    }

    @Override
    public CommonResult findAll() {
        return new CommonResult().success(wxFeedbackDao.findAll());
    }

    @Override
    public CommonResult add(WxFeedback wxFeedback) {
        setDefaultValue(wxFeedback);
        return new CommonResult().success(wxFeedbackDao.save(wxFeedback));
    }

    @Override
    public CommonResult modify(WxFeedback wxFeedback) {
        WxFeedback data = wxFeedbackDao.findById(wxFeedback.getId()).orElse(null);
        wxFeedback.setCreateTime(data.getCreateTime());
        wxFeedback.setDeleted(data.getDeleted());
        wxFeedback.setUpdateTime(LocalDateTime.now());
        return new CommonResult().success(wxFeedbackDao.save(wxFeedback));
    }

    @Override
    public CommonResult list(Pageable pageable,WxFeedbackQuery wxFeedbackQuery) {
        Specification<WxFeedback> specification = new Specification<WxFeedback>() {
            @Override
            public Predicate toPredicate(Root<WxFeedback> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                /*if(StringUtils.isNotBlank(wxFeedbackQuery)){
                    predicates.add(cb.like(root.get("courseName").as(String.class),"%"+wxFeedbackQuery.getCourseName()+"%"));
                }
                if(wxFeedback!=null){
                    predicates.add(cb.equal(root.get("state").as(Boolean.class),wxFeedbackQuery.getState()));
                }*/
                if (predicates.size() == 0) {
                    return null;
                }
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);
                return cb.and(predicateArr);
            }

        };
         return new CommonResult().success(wxFeedbackDao.findAll(specification,pageable));
    }

    @Override
    public CommonResult deleteById(Long id) {
        wxFeedbackDao.deleteById(id);
        return new CommonResult().success(!wxFeedbackDao.existsById(id));
    }


}