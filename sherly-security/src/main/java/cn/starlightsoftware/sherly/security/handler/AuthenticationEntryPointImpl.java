package cn.starlightsoftware.sherly.security.handler;

import cn.hutool.json.JSONUtil;
import cn.starlightsoftware.sherly.exception.enums.GlobalErrorCodeEnum;
import cn.starlightsoftware.sherly.model.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        Result<?> result = Result.error(GlobalErrorCodeEnum.UNAUTHORIZED);
        String jsonResult = JSONUtil.toJsonStr(result);

        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonResult);
    }
}
