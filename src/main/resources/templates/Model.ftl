import javax.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@SQLDelete(sql = "update ${model_name} set is_deleted=1 where id=?")
@Where(clause = "is_deleted = 0")
@Table(name = "${model_name}")
@Data
public class ${model_name_uc}  {

    <#if model_column?exists>
        <#list model_column as model>
    <#if model.column_name = 'id'>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    <#elseif model.column_name = 'create_time'>
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    <#elseif model.column_name = 'update_time'>
    /**
     * 上次更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    <#elseif model.column_name = 'is_deleted'>
    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Integer deleted;

    <#else>
    <#if (model.data_type = 'varchar' || model.data_type = 'text' || model.data_type = 'char')>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private String ${model.column_name_uc?uncap_first};

    </#if>
    <#if model.data_type = 'datetime'>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private LocalDateTime ${model.column_name_uc?uncap_first};

    </#if>
    <#if model.data_type = 'date'>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private LocalDate ${model.column_name_uc?uncap_first};

    </#if>
    <#if model.data_type = 'time'>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private LocalTime ${model.column_name_uc?uncap_first};

    </#if>
    <#if model.data_type = 'int'>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private Integer ${model.column_name_uc?uncap_first};

    </#if>
    <#if (model.data_type = 'int unsigned' || model.data_type = 'tinyint' || model.data_type = 'tinyint unsigned')>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private Integer ${model.column_name_uc?uncap_first};

    </#if>
    <#if model.data_type = 'bigint unsigned'>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private Long ${model.column_name_uc?uncap_first};

    </#if>
    <#if model.data_type = 'bigint'>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private Long ${model.column_name_uc?uncap_first};

    </#if>
    <#if model.data_type = 'bit' || model.data_type = 'tinyint unsigned'>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private Integer ${model.column_name_uc?uncap_first};

    </#if>
    <#if (model.data_type = 'decimal unsigned' || model.data_type = 'decimal')>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private BigDecimal ${model.column_name_uc?uncap_first};

    </#if>
    <#if model.data_type = 'json'>
    /**
     * ${model.column_comment!}
     */
    @Column(name = "${model.column_name}")
    private JSONObject ${model.column_name_uc?uncap_first};

    </#if>
    </#if>
        </#list>
    </#if>

}