
import com.daishuaiqing.farmland.domain.WxPanel;
import com.daishuaiqing.farmland.service.WxPanelService;
import com.daishuaiqing.farmland.query.WxPanelQuery;
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
public class WxPanelController {

    @Autowired
    private WxPanelService wxPanelService;

    @ApiOperation("单个查询")
    @GetMapping("/wx_panel/find/{id}")
    public CommonResult findById(@PathVariable("id") Long id){
        return wxPanelService.findById(id);
    }

    @ApiOperation("全部")
    @GetMapping("/wx_panel/find/all")
    public CommonResult findAll(){
        return wxPanelService.findAll();
    }

    @ApiOperation("新增")
    @PostMapping("/wx_panel/add")
    public CommonResult add(@Valid @ApiParam @RequestBody WxPanel wxPanel,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return wxPanelService.add(wxPanel);
        }
    }

    @ApiOperation("删除")
    @GetMapping("/wx_panel/delete/{id}")
    public CommonResult deleteById(@PathVariable("id") Long id) {
        return wxPanelService.deleteById(id);
    }

    @ApiOperation("修改")
    @PostMapping("/wx_panel/modify/{id}")
    public CommonResult modify(@PathVariable(name = "id",required = true) Long id,
                               @Valid @ApiParam @RequestBody WxPanel wxPanel,
                               BindingResult bindingResult) {
        wxPanel.setId(id);
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return wxPanelService.modify(wxPanel);
        }
    }

    @ApiOperation("列表查询")
    @GetMapping("/wx_panel/list")
    public CommonResult list(PageParam page, WxPanelQuery wxPanelQuery) {
        Pageable pageable = PageRequest.of(page.getPage(),page.getSize());
        return wxPanelService.list(pageable,wxPanelQuery);
    }
}