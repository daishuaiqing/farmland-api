package com.daishuaiqing.farmland.common.web;

import com.daishuaiqing.farmland.common.generate.GenerateServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "代码生成器")
@RestController
public class GenerateController {

    @Autowired
    GenerateServiceImpl generateService;

    @ApiOperation("代码生成")
    @GetMapping("/generate/tableName")
    public Map<String,String> generateCode(@RequestParam(name="tableName",required = true) String tableName) {
        generateService.GenerateCode(tableName);
        Map<String, String> result = new HashMap<String,String>();
        result.put("msg","OK");
        return result;
    }

}
