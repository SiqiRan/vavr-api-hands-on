package org.experiment;

import io.vavr.collection.List;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class VavrMethodMap {
    VavrMethodMap instance;

    private VavrMethodMap() {
        this.instance = new VavrMethodMap();
    }

    private static final Map<String, Method> vavrMethodMapImplementation = new HashMap<>();

    static {
        try {
            vavrMethodMapImplementation.put("insert head", List.class.getMethod("prepend", Object.class));
            vavrMethodMapImplementation.put("insert middle", List.class.getMethod("insert", int.class, Object.class));
            vavrMethodMapImplementation.put("insert tail", List.class.getMethod("append", Object.class));
            vavrMethodMapImplementation.put("remove head", List.class.getMethod("tail"));
            vavrMethodMapImplementation.put("remove middle", List.class.getMethod("removeAt", int.class));
            vavrMethodMapImplementation.put("remove tail", List.class.getMethod("dropRight", int.class));
            vavrMethodMapImplementation.put("get", List.class.getMethod("get", int.class));
            vavrMethodMapImplementation.put("modify", List.class.getMethod("update", int.class, Object.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Method queryMethod(String actionWithPosition) {
        return vavrMethodMapImplementation.get(actionWithPosition);
    }
}
