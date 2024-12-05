package cn.starlightsoftware.sherly.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDetails {

    /** 登录用户编号 */
    private String loginUserId;

    /** 登录时间 */
    private String loginTime;

    /** 租户编号 */
    private String tenantId;

    /** 特殊操作数据库编号 */
    private String operateTenantId;

    /** 登录用户额外信息 */
    private Map<String, Object> info;

    /** 权限 */
    private Set<String> scopes;

    /** 上下文存储，只在一次请求中做临时缓存 */
    @JsonIgnore
    private Map<String, Object> context;

}
