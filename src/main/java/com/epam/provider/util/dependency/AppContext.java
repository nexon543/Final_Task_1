package com.epam.provider.util.dependency;

import com.epam.provider.util.resource.ResourceManager;

import java.util.HashMap;
import java.util.Map;

public class AppContext {
    private static Map<String, String> dependencyMap=ResourceManager.getDIMap();
    private static Map<String, Object> loadedBeans=new HashMap<>();
    private AppContext(){}

    public static Object getBean(String name) throws DependencyException {
        Object bean=loadedBeans.get(name);
        return bean != null ? bean:loadClass(name);
    }

    private static Object loadClass(String name) throws DependencyException {
        try {
            return Class.forName(dependencyMap.get(name)).newInstance();
        } catch (Exception e) {
           throw new DependencyException("can't find bean by name", e);
        }
    }
}
