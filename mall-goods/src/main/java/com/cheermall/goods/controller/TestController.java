package com.cheermall.goods.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LuoHaiYang
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PreAuthorize("hasAuthority('user:views')")
    @GetMapping(value = "/getGood")
    @ResponseBody
    public String getGoods() {
        return "Iphone 11 plus";
    }
}
