package cn.starlightsoftware.sherly.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author 谷子毅
 */
@Data
public abstract class BaseModel {

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

    /*
        逻辑删除
        如果要使用逻辑删除功能，请在 继承了 BaseModel 的父类添加 deleted 字段，并使用注解 @TableLogic
        关于 mybatis-plus 逻辑删除的详情请移步 mybatis-plus 官方文档

        JAVA:
        /-- 逻辑删除 -/
        @TableLogic
        private BooleanEnum deleted;

        SQL:
        `deleted` tinyint NOT NULL DEFAULT 0,

     */

}
