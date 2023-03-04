package org.dataStructures;

import org.experiment.Execution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

class PerformanceExperimentTest {

    ArrayList<Integer> arrayList = new ArrayList<>();
    ArrayList<Object> emptyList = new ArrayList<>();
    ArrayList<Object> headList = new ArrayList<>(Collections.singletonList(0));
    ArrayList<Object> twoParamsList = new ArrayList<>(Arrays.asList(0, 1));
    List<Execution> executionList = new ArrayList<>();
    io.vavr.collection.List<Integer> vavrList = io.vavr.collection.List.of();

    long TIMES = 1000;

    @BeforeEach
    void setUp() {
        executionList.add(new Execution("insert head", headList, arrayList, TIMES));
        executionList.add(new Execution("insert middle", twoParamsList, arrayList, TIMES));
        executionList.add(new Execution("insert tail", twoParamsList, arrayList, TIMES));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFunctionalities() throws InvocationTargetException, IllegalAccessException {
        for (Execution execution : executionList) {
            execution.execute();
        }
    }
}