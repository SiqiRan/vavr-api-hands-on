package org.experiment;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Execution {
    private static final Logger logger = Logger.getLogger(Execution.class);
    Method method;
    String methodName;
    List<Object> parameters;
    Serializable dataStructure;
    long times;

    public Execution(String methodName, List<Object> parameters, Serializable dataStructure, long times) {
        this.methodName = methodName;
        if (dataStructure.getClass() == java.util.ArrayList.class || dataStructure.getClass() == java.util.LinkedList.class) {
            this.method = JavaMethodMap.queryMethod(methodName);
        } else if (dataStructure.getClass() == io.vavr.collection.List.Cons.class) {
            this.method = VavrMethodMap.queryMethod(methodName);
        }
        this.parameters = parameters;
        this.dataStructure = dataStructure;
        this.times = times;
    }

    public void execute() throws InvocationTargetException, IllegalAccessException {
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
    }


    private void executeWithNoParam() throws InvocationTargetException, IllegalAccessException {
        for (long i = 0; i < this.times; i++) {
            method.invoke(dataStructure);
        }
    }

    private void executeWithOneParam(Object param) throws InvocationTargetException, IllegalAccessException {
        for (long i = 0; i < this.times; i++) {
            if (methodName.endsWith("middle")) {
                method.invoke(dataStructure, (int) i / 2);
            } else {
                method.invoke(dataStructure, param);
            }
        }
    }

    private void executeWithTwoParams(Object param, Object param1) throws InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < this.times; i++) {
            if (methodName.endsWith("middle")) {
                method.invoke(dataStructure, i / 2, param1);
            } else if (methodName.endsWith("tail")) {
                method.invoke(dataStructure, i, param1);
            } else {
                method.invoke(dataStructure, param, param1);
            }
        }
    }

    private Integer modify(Object param) {
        return (Integer) param / 2;
    }
}
