package com.cheermall.common.interceptor;

import com.cheermall.common.CheerMallUtil;
import com.cheermall.common.entity.constant.CheerMallConstant;
import com.cheermall.common.system.CheerMallResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: LuoHaiYang
 */
public class ServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取Gateway Token
        String token = request.getHeader(CheerMallConstant.GATEWAY_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(CheerMallConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        // 校验Gateway Token的正确性
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {
            CheerMallResponse cheerMallResponse = new CheerMallResponse();
            CheerMallUtil.makeResponse(response, MediaType.APPLICATION_JSON_VALUE,
                    HttpServletResponse.SC_FORBIDDEN, cheerMallResponse.message("请通过网关获取资源"));
            return false;
        }
    }
}
