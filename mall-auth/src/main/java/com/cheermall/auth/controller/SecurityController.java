package com.cheermall.auth.controller;

import com.cheermall.auth.service.ValidateCodeService;
import com.cheermall.common.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @Author: LuoHaiYang
 */
@RestController
public class SecurityController {
    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }
}
