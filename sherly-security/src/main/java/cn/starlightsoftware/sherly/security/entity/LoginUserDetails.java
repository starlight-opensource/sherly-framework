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

    /** 租户代码 */
    private String tenantCode;

    /** 特殊操作数据库代码 */
    private String operateTenantCode;

    /** 登录用户额外信息 */
    private Map<String, String> info;

    /** 权限 */
    private Set<String> scopes;

    /** 上下文存储，只在一次请求中做临时缓存 */
    @JsonIgnore
    private Map<String, Object> context;

}
