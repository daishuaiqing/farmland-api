import javax.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@SQLDelete(sql = "update wx_panel set is_deleted=1 where id=?")
@Where(clause = "is_deleted = 0")
@Table(name = "wx_panel")
@Data
public class WxPanel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 微信用户id
     */
    @Column(name = "wx_uid")
    private Long wxUid;

    /**
     * 发布数
     */
    @Column(name = "upload_cnt")
    private Integer uploadCnt;

    /**
     * 收藏数
     */
    @Column(name = "collecton_cnt")
    private Integer collectonCnt;

    /**
     * 关注数
     */
    @Column(name = "follow_cnt")
    private Integer followCnt;

    /**
     * 粉丝数
     */
    @Column(name = "fans_cnt")
    private Integer fansCnt;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 上次更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Integer deleted;


}