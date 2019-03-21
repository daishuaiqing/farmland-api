<#list model_column as model>
${model_name_uc?uncap_first}.set${model.column_name_uc}();//设置 ${model.column_comment!} 的默认值
</#list>