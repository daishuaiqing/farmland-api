package com.daishuaiqing.farmland.common.upload;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PathConfig {
    //图片上传路径
    @Value("${resource.path.image}")
    private String imagePath;
    //文件上传路径
    @Value("${resource.path.file}")
    private String filePath;
    //临时文件路径
    //private String tempPath;
    //静态资源
    //private String staticPath;
    //根路径
    //private String rootPath;
}
