package cn.starlightsoftware.sherly.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @author 谷子毅
 */
@Data
public abstract class BaseDO {

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 创建人编号 */
    @TableField(fill = FieldFill.INSERT)
    private String createUserId;

    /** 更新人编号 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

    /** 租户编号 */
    private String tenantId;

    /** 逻辑删除 */
    @TableLogic
    private Boolean deleted;

}
