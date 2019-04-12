package com.daishuaiqing.farmland.common.web;

import com.daishuaiqing.farmland.common.upload.PathConfig;
import com.daishuaiqing.farmland.common.upload.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class UploadConroller {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private PathConfig pathConfig;

    /**
     * 上传图片(单张)
     * @param image 上传的文件
     * @return
     */
    @ApiOperation("图片上传")
    @PostMapping(value="/upload/image")
    public Map<String,String> upload(@RequestParam("image") MultipartFile image) throws Exception {
        Map<String,String> result = new ConcurrentHashMap<>();
        result.put("url",uploadService.saveImageByMultipartFile(image));
        return result;
    }

    @ApiOperation("批量上传图片")
    @PostMapping(value="/upload/batch/image")
    public List<String> batchUpload(@RequestParam("images") MultipartFile[] images) throws Exception {
        List<String> imagesUrlList = new ArrayList<>();
        for(MultipartFile image: images){
            String url = uploadService.saveImageByMultipartFile(image);
            imagesUrlList.add(url);
        }
        return imagesUrlList;
    }


    @ApiOperation("获取图片")
    @GetMapping("/image/{dateStr}/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String dateStr, @PathVariable String filename) {
        /*获取图片的所在路径*/
        Path file = Paths.get(pathConfig.getImagePath()+dateStr+"/"+filename);
        Resource resource=null;
        try {
            /*由路径获取到资源*/
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    @ApiOperation("文件上传")
    @PostMapping(value="/upload/file")
    public Map<String,Object> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        /*获取图片的原始名称*/
        String fileName = LocalDate.now().toString()+file.getOriginalFilename();
        /*重命名文件名称*/
        //String imageName= UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        /*图片存储的父级目录用时间命名*/
        //String dateStr= DateUtil.formatDate(new Date(),"yyyyMMdd");
        /*存储最终返回结果*/
        Map<String,Object> result=new HashMap<String,Object>();
        try {
            /*存储图片*/
            uploadService.saveFile(file.getBytes(),fileName);
        } catch (Exception e) {
            /*如果存储发生异常，则返回图片出处发生异常的结果*/
            result.put("statusCode",500);
            result.put("erroMsg","文件存储失败，请检查存储路径是否存在或者是否有权限!");
            return result;
        }
        /*图片存储成功之后,返回存储的路径*/
        result.put("statusCode",200);
        result.put("url",pathConfig.getFilePath()+fileName);
        return result;
    }
}
