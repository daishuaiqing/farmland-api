
import com.daishuaiqing.farmland.domain.Gallery;
import com.daishuaiqing.farmland.service.GalleryService;
import com.daishuaiqing.farmland.query.GalleryQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import com.daishuaiqing.farmland.query.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @ApiOperation("单个查询")
    @GetMapping("/gallery/find/{id}")
    public CommonResult findById(@PathVariable("id") Long id){
        return galleryService.findById(id);
    }

    @ApiOperation("全部")
    @GetMapping("/gallery/find/all")
    public CommonResult findAll(){
        return galleryService.findAll();
    }

    @ApiOperation("新增")
    @PostMapping("/gallery/add")
    public CommonResult add(@Valid @ApiParam @RequestBody Gallery gallery,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return galleryService.add(gallery);
        }
    }

    @ApiOperation("删除")
    @GetMapping("/gallery/delete/{id}")
    public CommonResult deleteById(@PathVariable("id") Long id) {
        return galleryService.deleteById(id);
    }

    @ApiOperation("修改")
    @PostMapping("/gallery/modify/{id}")
    public CommonResult modify(@PathVariable(name = "id",required = true) Long id,
                               @Valid @ApiParam @RequestBody Gallery gallery,
                               BindingResult bindingResult) {
        gallery.setId(id);
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return galleryService.modify(gallery);
        }
    }

    @ApiOperation("列表查询")
    @GetMapping("/gallery/list")
    public CommonResult list(PageParam page, GalleryQuery galleryQuery) {
        Pageable pageable = PageRequest.of(page.getPage(),page.getSize());
        return galleryService.list(pageable,galleryQuery);
    }
}