package com.daishuaiqing.farmland.service.impl;

import com.daishuaiqing.farmland.domain.WxPanel;
import com.daishuaiqing.farmland.dao.WxPanelDao;
import com.daishuaiqing.farmland.service.WxPanelService;
import com.daishuaiqing.farmland.query.WxPanelQuery;
import com.daishuaiqing.farmland.util.UserInfoUtils;
import com.daishuaiqing.farmland.vo.CommonResult;
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
public class WxPanelServiceImpl implements WxPanelService {

    @Autowired
    private WxPanelDao wxPanelDao;
    @Autowired
    private UserInfoUtils userInfoUtils;

    /**
    * wxPanel 设置默认值
    * 创建时间，更新时间，是否删除
    * @param wxPanel
    */
    private void setDefaultValue(WxPanel wxPanel) {
        wxPanel.setCreateTime(LocalDateTime.now());
        wxPanel.setUpdateTime(LocalDateTime.now());
        wxPanel.setDeleted(0);
    }

    @Override
    public CommonResult findByWxUserId(Long id) {
        WxPanel wxPanel = wxPanelDao.findByWxUid(id);
        if(ObjectUtils.isEmpty(wxPanel)){
            WxPanel insert = new WxPanel();
            setDefaultValue(insert);
            insert.setCollectonCnt(0);
            insert.setFansCnt(0);
            insert.setFollowCnt(0);
            insert.setWxUid(id);
            insert.setUploadCnt(0);
            wxPanelDao.save(insert);
            wxPanel = insert;
        }
        return new CommonResult().success(wxPanel);
    }

    @Override
    public CommonResult findAll() {
        return new CommonResult().success(wxPanelDao.findAll());
    }

    @Override
    public CommonResult add(WxPanel wxPanel) {
        setDefaultValue(wxPanel);
        return new CommonResult().success(wxPanelDao.save(wxPanel));
    }

    @Override
    public CommonResult modify(WxPanel wxPanel) {
        WxPanel data = wxPanelDao.findById(wxPanel.getId()).orElse(null);
        wxPanel.setCreateTime(data.getCreateTime());
        wxPanel.setDeleted(data.getDeleted());
        wxPanel.setUpdateTime(LocalDateTime.now());
        return new CommonResult().success(wxPanelDao.save(wxPanel));
    }

    @Override
    public CommonResult list(Pageable pageable,WxPanelQuery wxPanelQuery) {
        Specification<WxPanel> specification = new Specification<WxPanel>() {
            @Override
            public Predicate toPredicate(Root<WxPanel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                /*if(StringUtils.isNotBlank(wxPanelQuery)){
                    predicates.add(cb.like(root.get("courseName").as(String.class),"%"+wxPanelQuery.getCourseName()+"%"));
                }
                if(wxPanel!=null){
                    predicates.add(cb.equal(root.get("state").as(Boolean.class),wxPanelQuery.getState()));
                }*/
                if (predicates.size() == 0) {
                    return null;
                }
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);
                return cb.and(predicateArr);
            }

        };
         return new CommonResult().success(wxPanelDao.findAll(specification,pageable));
    }

    @Override
    public CommonResult deleteById(Long id) {
        wxPanelDao.deleteById(id);
        return new CommonResult().success(!wxPanelDao.existsById(id));
    }

    @Override
    public void updateUploadCnt(Long wxUid, int size) {
        WxPanel wxPanel = wxPanelDao.findByWxUid(wxUid);
        wxPanel.setUploadCnt(wxPanel.getUploadCnt() + size);
        wxPanelDao.save(wxPanel);
    }


}