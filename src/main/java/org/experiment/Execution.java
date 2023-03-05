package org.experiment;

import lombok.Getter;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Getter
public class Execution {
    private static final Logger logger = Logger.getLogger(Execution.class);
    Method method;
    String methodName;
    List<Object> parameters;
    Serializable dataStructure;
    boolean methodResultSerializable;
    long times;

    public Execution(String methodName, List<Object> parameters, Serializable dataStructure, long times) {
        this.methodName = methodName;
        if (dataStructure.getClass() == java.util.ArrayList.class || dataStructure.getClass() == java.util.LinkedList.class) {
            this.method = JavaMethodMap.queryMethod(methodName);
        } else if (dataStructure.getClass() == io.vavr.collection.List.Cons.class || dataStructure.getClass() == io.vavr.collection.List.Nil.class) {
            this.method = VavrMethodMap.queryMethod(methodName);
        }
        this.parameters = parameters;
        this.dataStructure = dataStructure;
        this.times = times;
        methodResultSerializable = Serializable.class.isAssignableFrom(method.getReturnType());
    }

    public Serializable execute() throws InvocationTargetException, IllegalAccessException {
        long startTime = System.nanoTime();
        switch (method.getParameterCount()) {
            case 0:
                executeWithNoParam();
                break;
            case 1:
                Object param1 = parameters.get(0);
                executeWithOneParam(param1);
                break;
            case 2:
                Object param2 = parameters.get(0);
                Object param3 = parameters.get(1);
                executeWithTwoParams(param2, param3);
                break;
            default:
                throw new RuntimeException("method call failure");
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        logger.info(String.format("%s structure spends %s nano seconds in %s times of %s operation", dataStructure.getClass(), totalTime, times, methodName));
        return dataStructure;
    }


    private void executeWithNoParam() throws InvocationTargetException, IllegalAccessException {
        for (long i = 0; i < this.times; i++) {
            executeWithNoParamByClass();
        }
    }

    private void executeWithNoParamByClass() throws IllegalAccessException, InvocationTargetException {
        if (methodResultSerializable) {
            dataStructure = (Serializable) method.invoke(dataStructure);
        } else {
            method.invoke(dataStructure);
        }
    }

    private void executeWithOneParamByClass(Object param) throws IllegalAccessException, InvocationTargetException {
        if (methodResultSerializable) {
            dataStructure = (Serializable) method.invoke(dataStructure, param);
        } else {
            method.invoke(dataStructure, param);
        }
    }

    private void executeWithTwoParamsByClass(Object param, Object param2) throws IllegalAccessException, InvocationTargetException {
        if (methodResultSerializable) {
            dataStructure = (Serializable) method.invoke(dataStructure, param, param2);
        } else {
            method.invoke(dataStructure, param, param2);
        }
    }

    private void executeWithOneParam(Object param) throws InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < this.times; i++) {
            if (methodName.endsWith("insert middle")) {
                executeWithOneParamByClass(i / 2);
            } else if (methodName.endsWith("remove middle")) {
                executeWithOneParamByClass((int) (times - i) / 2);
            } else if (methodName.endsWith("insert tail")) {
                executeWithOneParamByClass(i);
            } else if (methodName.endsWith("remove tail")) {
                executeWithOneParamByClass((int) (times - i) - 1);
            } else {
                executeWithOneParamByClass(param);
            }
        }
    }

    private void executeWithTwoParams(Object param, Object param1) throws InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < this.times; i++) {
            if (methodName.endsWith("insert middle")) {
                executeWithTwoParamsByClass(i / 2, param1);
            } else if (methodName.endsWith("remove middle")) {
                executeWithTwoParamsByClass((int) (times - i) / 2, param1);
            } else if (methodName.endsWith("insert tail")) {
                executeWithTwoParamsByClass(i, param1);
            } else if (methodName.endsWith("remove tail")) {
                executeWithTwoParamsByClass((int) (times - i) - 1, param1);
            } else {
                executeWithTwoParamsByClass(param, param1);
            }
        }
    }

}
