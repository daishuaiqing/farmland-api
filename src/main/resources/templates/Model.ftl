import javax.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="${model_name_uc}")
@SQLDelete(sql = "update ${model_name} set is_deleted=1 where id=?")
@Where(clause = "is_deleted = 0")
@Table(name = "${model_name}")
@Data
public class ${model_name_uc}  {

    <#if model_column?exists>
        <#list model_column as model>
    <#if model.column_name = 'id'>
    @ApiModelProperty(hidden=true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    <#elseif model.column_name = 'create_time'>
    @ApiModelProperty(hidden=true)
    @Column(name = "create_time")
    private Timestamp createTime;
    <#elseif model.column_name = 'update_time'>
    @ApiModelProperty(hidden=true)
    @Column(name = "update_time")
    private Timestamp updateTime;
    <#elseif model.column_name = 'is_deleted'>
    @ApiModelProperty(hidden=true)
    @Column(name = "is_deleted")
    private Integer deleted;
    <#else>
    <#if (model.data_type = 'varchar' || model.data_type = 'text' || model.data_type = 'char')>
    @Column(name = "${model.column_name}")
    private String ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if model.data_type = 'datetime'>
    @Column(name = "${model.column_name}")
    private Timestamp ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if model.data_type = 'date'>
    @Column(name = "${model.column_name}")
    private Date ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if model.data_type = 'time'>
    @Column(name = "${model.column_name}")
    private Time ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if model.data_type = 'int'>
    @Column(name = "${model.column_name}")
    private Integer ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if (model.data_type = 'int unsigned' || model.data_type = 'tinyint' || model.data_type = 'tinyint unsigned')>
    @Column(name = "${model.column_name}")
    private Integer ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if model.data_type = 'bigint unsigned'>
    @Column(name = "${model.column_name}")
    private Long ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if model.data_type = 'bigint'>
    @Column(name = "${model.column_name}")
    private Long ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if model.data_type = 'bit' || model.data_type = 'tinyint unsigned'>
    @Column(name = "${model.column_name}")
    private Integer ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if (model.data_type = 'decimal unsigned' || model.data_type = 'decimal')>
    @Column(name = "${model.column_name}")
    private BigDecimal ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    <#if model.data_type = 'json'>
    @Column(name = "${model.column_name}")
    private JSONObject ${model.column_name_uc?uncap_first};//${model.column_comment!}
    </#if>
    </#if>
        </#list>
    </#if>

}