import ${package_name}.model.R;
import ${package_name}.domain.${model_name_uc};
import ${package_name}.service.${model_name_uc}Service;
import ${package_name}.model.MyPage;
import ${package_name}.query.${model_name_uc}Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "")
@RestController
public class ${model_name_uc}Controller {

    @Autowired
    private ${model_name_uc}Service ${model_name_uc?uncap_first}Service;

    @ApiOperation("单个查询")
    @GetMapping("/${model_name}/find/{id}")
    public R findById(@PathVariable("id") Long id){
        try {
            return R.isOk().data(${model_name_uc?uncap_first}Service.findById(id));
        }catch (Exception e){
            return R.isFail(e);
        }
    }

    @ApiOperation("全部")
    @GetMapping("/${model_name}/find/all")
    public R findAll(){
        try {
            return R.isOk().data(${model_name_uc?uncap_first}Service.findAll());
        }catch (Exception e){
            return R.isFail(e);
        }
    }

    @ApiOperation("新增")
    @PostMapping("/${model_name}/add")
    public R add(@Valid @ApiParam @RequestBody ${model_name_uc} ${model_name_uc?uncap_first}, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return R.isValid(bindingResult.getFieldError().getDefaultMessage());
        }else {
            try {
                return R.isOk().data(${model_name_uc?uncap_first}Service.add(${model_name_uc?uncap_first}));
            }catch (Exception e){
                return R.isFail(e);
            }
        }
    }

    @ApiOperation("删除")
    @DeleteMapping("/${model_name}/delete/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        try {
            return R.isOk().data(${model_name_uc?uncap_first}Service.deleteById(id));
        }catch (Exception e){
            return R.isFail(e);
        }
    }

    @ApiOperation("修改")
    @PutMapping("/${model_name}/modify/{id}")
    public R modify(@PathVariable(name = "id",required = true) Long id,@Valid @ApiParam @RequestBody ${model_name_uc} ${model_name_uc?uncap_first}, BindingResult bindingResult) {
        ${model_name_uc?uncap_first}.setId(id);
        if(bindingResult.hasErrors()){
            return R.isValid(bindingResult.getFieldError().getDefaultMessage());
        }else {
            try {
                return R.isOk().data(${model_name_uc?uncap_first}Service.modify(${model_name_uc?uncap_first}));
            }catch (Exception e){
                return R.isFail(e);
            }
        }
    }

    @ApiOperation("列表查询")
    @GetMapping(value = "/${model_name}/list")
    public R list(MyPage page, ${model_name_uc}Query ${model_name_uc?uncap_first}Query) {
        Pageable pageable = PageRequest.of(page.getPage(),page.getSize());
        try {
            return R.isOk().data(${model_name_uc?uncap_first}Service.list(pageable,${model_name_uc?uncap_first}Query));
        }catch (Exception e){
            return R.isFail(e);
        }
    }
}