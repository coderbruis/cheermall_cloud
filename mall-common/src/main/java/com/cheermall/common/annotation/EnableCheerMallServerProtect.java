package com.cheermall.common.annotation;

import com.cheermall.common.configure.ServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: LuoHaiYang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerProtectConfigure.class)
public @interface EnableCheerMallServerProtect {
}
