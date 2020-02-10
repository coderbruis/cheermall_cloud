package com.cheermall.common.annotation;

import com.cheermall.common.selector.CheerMallCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: LuoHaiYang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CheerMallCloudApplicationSelector.class)
public @interface CheerMallCloudApplication {
}
