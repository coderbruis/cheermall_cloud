package com.cheermall.common.handler;

import com.cheermall.common.CheerMallUtil;
import com.cheermall.common.system.CheerMallResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: LuoHaiYang
 */
public class CheerMallAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        CheerMallResponse cheerMallResponse = new CheerMallResponse();
        CheerMallUtil.makeResponse(
                httpServletResponse,
                MediaType.APPLICATION_JSON_VALUE,
                httpServletResponse.SC_UNAUTHORIZED,
                cheerMallResponse.message("token无效")
        );
    }
}
