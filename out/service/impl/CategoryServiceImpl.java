import com.daishuaiqing.farmland.domain.Category;
import com.daishuaiqing.farmland.dao.CategoryDao;
import com.daishuaiqing.farmland.service.CategoryService;
import com.daishuaiqing.farmland.query.CategoryQuery;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public CommonResult findById(Long id) {
        return new CommonResult().success(categoryDao.findById(id).orElse(null));
    }

    @Override
    public CommonResult findAll() {
        return new CommonResult().success(categoryDao.findAll());
    }

    @Override
    public CommonResult add(Category category) {
        category.setCreateTime(new Timestamp(System.currentTimeMillis()));
        category.setDeleted(0);
        category.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return categoryDao.save(category);
    }

    @Override
    public CommonResult modify(Category category) {
        Category data = categoryDao.findById(category.getId()).orElse(null);
        category.setCreateTime(data.getCreateTime());
        category.setDeleted(data.getDeleted());
        category.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return categoryDao.save(category);
    }

    @Override
    public CommonResult list(Pageable pageable,CategoryQuery categoryQuery) {
        Specification<Category> specification = new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                /*if(StringUtils.isNotBlank(categoryQuery)){
                    predicates.add(cb.like(root.get("courseName").as(String.class),"%"+categoryQuery.getCourseName()+"%"));
                }
                if(category!=null){
                    predicates.add(cb.equal(root.get("state").as(Boolean.class),categoryQuery.getState()));
                }*/
                if (predicates.size() == 0) {
                    return null;
                }
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);
                return cb.and(predicateArr);
            }

        };
         return categoryDao.findAll(specification,pageable);
    }

    @Override
    public CommonResult deleteById(Long id) {
        categoryDao.deleteById(id);
        return !categoryDao.existsById(id);
    }


}