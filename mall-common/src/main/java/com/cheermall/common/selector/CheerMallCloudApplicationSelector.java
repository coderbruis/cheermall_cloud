package com.cheermall.common.selector;

import com.cheermall.common.configure.CheerMallAuthExceptionConfigure;
import com.cheermall.common.configure.CheerMallOAuth2FeignConfigure;
import com.cheermall.common.configure.ServerProtectConfigure;
import lombok.NonNull;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: LuoHaiYang
 */
public class CheerMallCloudApplicationSelector implements ImportSelector {
    @Override
    @SuppressWarnings("all")
    public String[] selectImports(@NonNull AnnotationMetadata annotationMetadata) {
        return new String[] {
                CheerMallAuthExceptionConfigure.class.getName(),
                CheerMallOAuth2FeignConfigure.class.getName(),
                ServerProtectConfigure.class.getName()
        };
    }
}
