import ${package_name}.domain.${model_name_uc};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ${model_name_uc}Dao extends JpaRepository<${model_name_uc}, Long>,JpaSpecificationExecutor<${model_name_uc}> {
 }