package com.cheermall.common.annotation;

import com.cheermall.common.configure.CheerMallAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: LuoHaiYang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CheerMallAuthExceptionConfigure.class)
public @interface EnableCheerMallAuthExceptionHandler {
}
