package com.cheermall.common.annotation;

import com.cheermall.common.configure.LettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: LuoHaiYang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LettuceRedisConfigure.class)
public @interface EnableCheerMallLettuceRedis {
}
