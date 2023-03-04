package org.experiment;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JavaMethodMap {

    JavaMethodMap instance;

    private static final Map<String, Method> javaMethodMapImplementation = new HashMap<>();

    private JavaMethodMap() {
        this.instance = new JavaMethodMap();
    }

    static {
        try {
            javaMethodMapImplementation.put("insert head", List.class.getMethod("add", Object.class));
            javaMethodMapImplementation.put("insert middle", List.class.getMethod("add", int.class, Object.class));
            javaMethodMapImplementation.put("insert tail", List.class.getMethod("add", int.class, Object.class));
            javaMethodMapImplementation.put("remove head", List.class.getMethod("remove", int.class));
            javaMethodMapImplementation.put("remove middle", List.class.getMethod("remove", int.class));
            javaMethodMapImplementation.put("remove tail", List.class.getMethod("remove", int.class));
            javaMethodMapImplementation.put("get", List.class.getMethod("get", int.class));
            javaMethodMapImplementation.put("modify", List.class.getMethod("set", int.class, Object.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Method queryMethod(String actionWithPosition) {
        return javaMethodMapImplementation.get(actionWithPosition);
    }
}
