package com.daishuaiqing.farmland.common.generate;

import com.daishuaiqing.farmland.util.FreeMarkerTemplateUtils;
import com.daishuaiqing.farmland.util.StringUtils;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成实现类
 */
@Service
public class GenerateServiceImpl {

    @Autowired
    GenerateDaoImpl generateDao;
    @Value("${code.generate.package}")
    private String packageName;

    public void GenerateCode(String tableName) {
        //输出路径
        File path = new File(System .getProperty("user.dir")+"\\out");
        if(!path.exists()) path.mkdirs();
        File controllerPath = new File(System .getProperty("user.dir")+"\\out\\controller");
        if(!controllerPath.exists()) controllerPath.mkdirs();
        File servicePath = new File(System .getProperty("user.dir")+"\\out\\service");
        if(!servicePath.exists()) servicePath.mkdirs();
        File serviceImplPath = new File(System .getProperty("user.dir")+"\\out\\service\\impl");
        if(!serviceImplPath.exists()) serviceImplPath.mkdirs();
        File daoPath = new File(System .getProperty("user.dir")+"\\out\\dao");
        if(!daoPath.exists()) daoPath.mkdirs();
        File modelPath = new File(System .getProperty("user.dir")+"\\out\\domain");
        if(!modelPath.exists()) modelPath.mkdirs();
        File queryPath = new File(System .getProperty("user.dir")+"\\out\\query");
        if(!queryPath.exists()) queryPath.mkdirs();
//        if(tableName.equals("all")){
//            List<String> allTables = generateDao.findAllTables();
//            for(String table:allTables){
//                List<Map<String,Object>> columns = generateDao.findColumns(table);
//                for(Map<String,Object> column:columns){
//                    column.put("column_name_uc",StringUtil.replaceUnderLineAndUpperCase(column.get("column_name").toString()));
//                }
//                Map<String,Object> dataMap = new HashMap<String,Object>();
//                dataMap.put("model_column",columns);
//                dataMap.put("model_name",table);
//                dataMap.put("model_name_uc",StringUtil.replaceUnderLineAndUpperCase(table));
//                generateFileByTemplate("Model.ftl",new File(path+"\\"+StringUtil.replaceUnderLineAndUpperCase(table)+".java"),dataMap);
//            }
//        }else{
        List<Map<String,Object>> columns = generateDao.findColumns(tableName);
        for(Map<String,Object> column:columns){
            column.put("column_name_uc", StringUtils.replaceUnderLineAndUpperCase(column.get("column_name").toString()));
        }
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("model_column",columns);
        dataMap.put("model_name",tableName);
        dataMap.put("package_name",packageName);
        dataMap.put("model_name_uc",StringUtils.replaceUnderLineAndUpperCase(tableName));
        generateFileByTemplate("Model.ftl",new File(modelPath+"\\"+StringUtils.replaceUnderLineAndUpperCase(tableName)+".java"),dataMap);
        generateFileByTemplate("Controller.ftl",new File(controllerPath+"\\"+StringUtils.replaceUnderLineAndUpperCase(tableName)+"Controller.java"),dataMap);
        generateFileByTemplate("SetValue.ftl",new File(path+"\\"+StringUtils.replaceUnderLineAndUpperCase(tableName)+"SetValue.java"),dataMap);
        generateFileByTemplate("Dao.ftl",new File(daoPath+"\\"+StringUtils.replaceUnderLineAndUpperCase(tableName)+"Dao.java"),dataMap);
        generateFileByTemplate("Service.ftl",new File(servicePath+"\\"+StringUtils.replaceUnderLineAndUpperCase(tableName)+"Service.java"),dataMap);
        generateFileByTemplate("ServiceImpl.ftl",new File(serviceImplPath+"\\"+StringUtils.replaceUnderLineAndUpperCase(tableName)+"ServiceImpl.java"),dataMap);
        generateFileByTemplate("Query.ftl",new File(queryPath+"\\"+StringUtils.replaceUnderLineAndUpperCase(tableName)+"Query.java"),dataMap);
        //}
    }



    /**
     * 通过模板生成文件
     * @param templateName
     * @param file
     * @param dataMap
     * @throws Exception
     */
    private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap){
        Template template = null;
        try {
            template = FreeMarkerTemplateUtils.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            template.process(dataMap,out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cleanAllData() {
        generateDao.cleanAllData();
    }
}
