package com.cheermall.common.system;

import java.util.HashMap;

/**
 * @Author: LuoHaiYang
 */
public class CheerMallResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public CheerMallResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public CheerMallResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public CheerMallResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
