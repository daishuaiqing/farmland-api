package com.daishuaiqing.farmland.common.generate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GenerateDaoImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Value("${code.generate.database}")
    private String databaseName;

    /**
     * 查询所有表名称
     * @return
     */
    public List<String> findAllTables(){
        String sql = "SHOW TABLES;";
        return jdbcTemplate.queryForList(sql,String.class);
    }

    /**
     * 查询列名
     * @param tableName
     * @return
     */
    public List<Map<String,Object>> findColumns(String tableName){
        String sql = "SELECT column_name,data_type,column_comment FROM information_schema.columns WHERE table_schema='"+databaseName+"' AND table_name=?;";
        return jdbcTemplate.queryForList(sql,tableName);
    }

    /**
     * 清理数据库所有数据
     */
    public void cleanAllData(){
        List<String> tableNames = findAllTables();
        for (String tableName: tableNames){
            jdbcTemplate.update("TRUNCATE "+tableName);
        }
    }

}
