package cn.starlightsoftware.sherly.model;

import lombok.Data;

/**
 * @author 谷子毅
 */
@Data
public abstract class BaseTenantDO extends BaseDO {

    /** 租户编号 */
    private String tenantId;

}
