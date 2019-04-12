package com.daishuaiqing.farmland.common.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadService {
    @Autowired
    private PathConfig pathConfig;
    /**
     *
     * @param file 需要存储的文件
     * @param imageName 需要存储的图片的名字
     * @param dateStr 格式为"yyyyMMdd"
     * @return
     */
    public void saveImage(byte[] file,String imageName,String dateStr) throws Exception {
        String imagePath=pathConfig.getImagePath()+ dateStr+"/";
        File targetFile = new File(imagePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(imagePath + imageName);
        out.write(file);
        out.flush();
        out.close();
    }

    public void saveFile(byte[] bytes, String fileName) throws Exception{
        File targetFile = new File(pathConfig.getImagePath());
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(pathConfig.getImagePath() + fileName);
        out.write(bytes);
        out.flush();
        out.close();
    }


    public String saveImageByMultipartFile(MultipartFile image) throws Exception {
        String fileName = image.getOriginalFilename();
        /*重命名文件名称*/
        String imageName= UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        /*图片存储的父级目录用时间命名*/
        String dateStr= LocalDate.now().toString();
        /*存储最终返回结果*/
        Map<String,Object> result=new HashMap<String,Object>();
        saveImage(image.getBytes(),imageName,dateStr);
        /*图片存储成功之后,返回存储的路径*/
        return "/image/"+dateStr+"/"+imageName;
    }
}
