import com.daishuaiqing.farmland.domain.${model_name_uc};
import com.daishuaiqing.farmland.dao.${model_name_uc}Dao;
import com.daishuaiqing.farmland.service.${model_name_uc}Service;
import com.daishuaiqing.farmland.query.${model_name_uc}Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class ${model_name_uc}ServiceImpl implements ${model_name_uc}Service {

    @Autowired
    private ${model_name_uc}Dao ${model_name_uc?uncap_first}Dao;

    @Override
    public ${model_name_uc} findById(Long id) {
        return ${model_name_uc?uncap_first}Dao.findById(id).orElse(null);
    }

    @Override
    public List<${model_name_uc}> findAll() {
        return ${model_name_uc?uncap_first}Dao.findAll();
    }

    @Override
    public ${model_name_uc} add(${model_name_uc} ${model_name_uc?uncap_first}) {
        ${model_name_uc?uncap_first}.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ${model_name_uc?uncap_first}.setDeleted(0);
        ${model_name_uc?uncap_first}.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return ${model_name_uc?uncap_first}Dao.save(${model_name_uc?uncap_first});
    }

    @Override
    public ${model_name_uc} modify(${model_name_uc} ${model_name_uc?uncap_first}) {
        ${model_name_uc} data = ${model_name_uc?uncap_first}Dao.findById(${model_name_uc?uncap_first}.getId()).orElse(null);
        ${model_name_uc?uncap_first}.setCreateTime(data.getCreateTime());
        ${model_name_uc?uncap_first}.setDeleted(data.getDeleted());
        ${model_name_uc?uncap_first}.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return ${model_name_uc?uncap_first}Dao.save(${model_name_uc?uncap_first});
    }

    @Override
    public Page<${model_name_uc}> list(Pageable pageable,${model_name_uc}Query ${model_name_uc?uncap_first}Query) {
        Specification<${model_name_uc}> specification = new Specification<${model_name_uc}>() {
            @Override
            public Predicate toPredicate(Root<${model_name_uc}> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                /*if(StringUtils.isNotBlank(${model_name_uc?uncap_first}Query)){
                    predicates.add(cb.like(root.get("courseName").as(String.class),"%"+${model_name_uc?uncap_first}Query.getCourseName()+"%"));
                }
                if(${model_name_uc?uncap_first}!=null){
                    predicates.add(cb.equal(root.get("state").as(Boolean.class),${model_name_uc?uncap_first}Query.getState()));
                }*/
                if (predicates.size() == 0) {
                    return null;
                }
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);
                return cb.and(predicateArr);
            }

        };
         return ${model_name_uc?uncap_first}Dao.findAll(specification,pageable);
    }

    @Override
    public Boolean deleteById(Long id) {
        ${model_name_uc?uncap_first}Dao.deleteById(id);
        return !${model_name_uc?uncap_first}Dao.existsById(id);
    }


}