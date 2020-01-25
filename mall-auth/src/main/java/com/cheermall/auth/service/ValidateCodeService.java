package com.cheermall.auth.service;

import com.cheermall.common.exception.ValidateCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: LuoHaiYang
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    void create(HttpServletRequest request, HttpServletResponse response)throws IOException, ValidateCodeException;

    /**
     * 校验验证码
     */
    void check(String key, String value) throws ValidateCodeException;
}
