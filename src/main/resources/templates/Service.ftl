import com.daishuaiqing.farmland.domain.${model_name_uc};
import com.daishuaiqing.farmland.query.${model_name_uc}Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ${model_name_uc}Service {

    ${model_name_uc} findById(Long id);

    List<${model_name_uc}> findAll();

    ${model_name_uc} add(${model_name_uc} ${model_name_uc?uncap_first});

    ${model_name_uc} modify(${model_name_uc} ${model_name_uc?uncap_first});

    Page<${model_name_uc}> list(Pageable pageable,${model_name_uc}Query ${model_name_uc?uncap_first}Query);

    Boolean deleteById(Long id);

}