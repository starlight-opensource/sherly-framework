package cn.starlightsoftware.sherly.security.utils;

import cn.hutool.core.map.MapUtil;
import cn.starlightsoftware.sherly.security.entity.LoginUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class SecurityUtil {

    public static LoginUserDetails getThreadLocalModel() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof LoginUserDetails) {
            return (LoginUserDetails) principal;
        }
        return new LoginUserDetails();
    }

    public static String getInfo(String key) {
        return getInfo(key, String.class);
    }

    public static <T> T getInfo(String key, Class<T> type) {
        Map<String, Object> info = getThreadLocalModel().getInfo();
        return MapUtil.get(info, key, type);
    }

    public static void setInfo(String key, String value) {
        LoginUserDetails threadLocalModel = getThreadLocalModel();
        Map<String, Object> info = threadLocalModel.getInfo();
        if (MapUtil.isEmpty(info)) {
            info = new HashMap<>();
        }
        info.put(key, value);
        threadLocalModel.setInfo(info);
        setThreadLocalModel(threadLocalModel);
    }

    public static String getLoginUserId() {
        return getThreadLocalModel().getLoginUserId();
    }

    public static String getLoginTime() {
        return getThreadLocalModel().getLoginTime();
    }

    public static String getTenantId() {
        return getThreadLocalModel().getTenantId();
    }

    public static Set<String> getScopes() {
        return getThreadLocalModel().getScopes();
    }

    public static void setOperateTenantId(String tenantId) {
        LoginUserDetails threadLocalModel = getThreadLocalModel();
        threadLocalModel.setTenantId(tenantId);
        setThreadLocalModel(threadLocalModel);
    }

    public static String getOperateTenantId() {
        return getThreadLocalModel().getTenantId();
    }

    public static void clearOperateTenantId() {
        LoginUserDetails threadLocalModel = getThreadLocalModel();
        threadLocalModel.setTenantId(null);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(threadLocalModel, null));
    }

    private static void setThreadLocalModel(LoginUserDetails loginUserDetails) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(loginUserDetails, null, authorities));
    }

    /**
     * 获取上下文对象，如果不存在则返回 NULL
     * 需要先在 SecurityConstants 中定义常量，如需要临时获取缓存变量 age → String AGE = "age";
     * 用法：Integer age = (Integer)SecurityUtil.getContext(AGE, Integer.class);
     *
     * @param key
     * @return
     */
    public static <T> T getContext(String key, Class<T> type) {
        Map<String, Object> context = getThreadLocalModel().getContext();
        return MapUtil.get(context, key, type);
    }

    /**
     * 设置上下文对象
     * 需要先在 SecurityConstants 中定义常量，如需要临时缓存变量 age → String AGE = "age";
     * 用法：SecurityUtil.setContext(AGE, 18);
     *
     * @param key
     * @param value
     */
    public static void setContext(String key, Object value) {
        Map<String, Object> context = getThreadLocalModel().getContext();
        if (MapUtil.isEmpty(context)) {
            context = new HashMap<>();
        }
        context.put(key, value);
        getThreadLocalModel().setContext(context);
    }

}
