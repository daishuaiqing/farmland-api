import com.daishuaiqing.farmland.domain.WxUser;
import com.daishuaiqing.farmland.query.WxUserQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Pageable;

public interface WxUserService {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(WxUser wxUser);

    CommonResult modify(WxUser wxUser);

    CommonResult list(Pageable pageable,WxUserQuery wxUserQuery);

    CommonResult deleteById(Long id);

}