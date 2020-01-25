package com.cheermall.common.handler;

import com.cheermall.common.CheerMallUtil;
import com.cheermall.common.system.CheerMallResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: LuoHaiYang
 */
public class CheerMallAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        CheerMallResponse response = new CheerMallResponse();
        CheerMallUtil.makeResponse(
                httpServletResponse,
                MediaType.APPLICATION_JSON_VALUE,
                HttpServletResponse.SC_FORBIDDEN,
                response.message("没有权限访问该资源"));
    }
}
