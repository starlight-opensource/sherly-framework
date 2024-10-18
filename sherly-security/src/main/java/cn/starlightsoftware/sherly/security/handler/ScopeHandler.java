package cn.starlightsoftware.sherly.security.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.starlightsoftware.sherly.security.utils.SecurityUtil;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author 谷子毅
 * @date 2024/5/9
 */
@Component("ss")
public class ScopeHandler {

    public boolean hasAnyScopes(String... scopes) {
        Set<String> scopeAll = SecurityUtil.getScopes();
        if (ArrayUtil.isEmpty(scopes)) {
            return true;
        }
        if (CollUtil.contains(scopeAll, "ROLE_admin")) {
            return true;
        }
        if (CollUtil.containsAny(scopeAll, CollUtil.newHashSet(scopes))) {
            return true;
        }
        return false;
    }

}
