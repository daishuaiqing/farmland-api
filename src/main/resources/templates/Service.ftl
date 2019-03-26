import ${package_name}.domain.${model_name_uc};
import ${package_name}.query.${model_name_uc}Query;
import ${package_name}.vo.CommonResult;
import org.springframework.data.domain.Pageable;

public interface ${model_name_uc}Service {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(${model_name_uc} ${model_name_uc?uncap_first});

    CommonResult modify(${model_name_uc} ${model_name_uc?uncap_first});

    CommonResult list(Pageable pageable,${model_name_uc}Query ${model_name_uc?uncap_first}Query);

    CommonResult deleteById(Long id);

}