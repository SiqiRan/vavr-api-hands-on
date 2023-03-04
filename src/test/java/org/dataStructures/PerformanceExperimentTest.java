package org.dataStructures;

import org.experiment.Execution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PerformanceExperimentTest {

    ArrayList<Integer> arrayList = new ArrayList<>();
    ArrayList<Object> emptyList = new ArrayList<>();
    ArrayList<Object> headList = new ArrayList<>(Collections.singletonList(0));
    ArrayList<Object> twoParamsList = new ArrayList<>(Arrays.asList(0, 1));
    List<Execution> executionList = new ArrayList<>();
    List<Execution> executionList2 = new ArrayList<>();
    io.vavr.collection.List<Integer> vavrList = io.vavr.collection.List.of(1);

    long TIMES = 1000;

    @BeforeEach
    void setUp() {
        executionList.add(new Execution("insert head", headList, arrayList, TIMES));
        executionList.add(new Execution("insert middle", twoParamsList, arrayList, TIMES));
        executionList.add(new Execution("insert tail", twoParamsList, arrayList, TIMES));
        executionList.add(new Execution("remove head", headList, arrayList, TIMES));
        executionList.add(new Execution("remove middle", headList, arrayList, TIMES));
        executionList.add(new Execution("remove tail", headList, arrayList, TIMES));
        executionList2.add(new Execution("insert head", headList, vavrList, TIMES));
        executionList2.add(new Execution("insert middle", twoParamsList, vavrList, TIMES));
        executionList2.add(new Execution("insert tail", twoParamsList, vavrList, TIMES));
        executionList2.add(new Execution("remove head", headList, vavrList, TIMES));
        executionList2.add(new Execution("remove middle", headList, vavrList, TIMES));
        executionList2.add(new Execution("remove tail", headList, vavrList, TIMES));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testArrayLists() throws InvocationTargetException, IllegalAccessException {
        for (Execution execution : executionList) {
            execution.execute();
        }
    }

//    @Test
//    void testVavrLists() throws InvocationTargetException, IllegalAccessException {
//        for (Execution execution : executionList2) {
//            execution.execute();
//        }
//    }
}