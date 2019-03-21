package com.daishuaiqing.farmland.common.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

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
}
