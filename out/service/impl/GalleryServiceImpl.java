import com.daishuaiqing.farmland.domain.Gallery;
import com.daishuaiqing.farmland.dao.GalleryDao;
import com.daishuaiqing.farmland.service.GalleryService;
import com.daishuaiqing.farmland.query.GalleryQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryDao galleryDao;

    /**
    * gallery 设置默认值
    * 创建时间，更新时间，是否删除
    * @param gallery
    */
    private void setDefaultValue(Gallery gallery) {
        gallery.setCreateTime(LocalDateTime.now());
        gallery.setUpdateTime(LocalDateTime.now());
        gallery.setDeleted(0);
    }

    @Override
    public CommonResult findById(Long id) {
        return new CommonResult().success(galleryDao.findById(id).orElse(null));
    }

    @Override
    public CommonResult findAll() {
        return new CommonResult().success(galleryDao.findAll());
    }

    @Override
    public CommonResult add(Gallery gallery) {
        setDefaultValue(gallery);
        return new CommonResult().success(galleryDao.save(gallery));
    }

    @Override
    public CommonResult modify(Gallery gallery) {
        Gallery data = galleryDao.findById(gallery.getId()).orElse(null);
        gallery.setCreateTime(data.getCreateTime());
        gallery.setDeleted(data.getDeleted());
        gallery.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return new CommonResult().success(galleryDao.save(gallery));
    }

    @Override
    public CommonResult list(Pageable pageable,GalleryQuery galleryQuery) {
        Specification<Gallery> specification = new Specification<Gallery>() {
            @Override
            public Predicate toPredicate(Root<Gallery> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                /*if(StringUtils.isNotBlank(galleryQuery)){
                    predicates.add(cb.like(root.get("courseName").as(String.class),"%"+galleryQuery.getCourseName()+"%"));
                }
                if(gallery!=null){
                    predicates.add(cb.equal(root.get("state").as(Boolean.class),galleryQuery.getState()));
                }*/
                if (predicates.size() == 0) {
                    return null;
                }
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);
                return cb.and(predicateArr);
            }

        };
         return new CommonResult().success(galleryDao.findAll(specification,pageable));
    }

    @Override
    public CommonResult deleteById(Long id) {
        galleryDao.deleteById(id);
        return new CommonResult().success(!galleryDao.existsById(id));
    }


}