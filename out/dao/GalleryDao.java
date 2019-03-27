import com.daishuaiqing.farmland.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GalleryDao extends JpaRepository<Gallery, Long>,JpaSpecificationExecutor<Gallery> {
 }