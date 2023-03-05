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

import static org.junit.jupiter.api.Assertions.assertEquals;

class PerformanceExperimentTest {

    ArrayList<Integer> arrayList = new ArrayList<>();
    ArrayList<Object> headList = new ArrayList<>(Collections.singletonList(0));
    ArrayList<Object> twoParamsList = new ArrayList<>(Arrays.asList(0, 1));
    List<Execution> javaExecutionList = new ArrayList<>();
    List<Execution> vavrExecutionList = new ArrayList<>();
    io.vavr.collection.List<Integer> vavrList = io.vavr.collection.List.of();

    long TIMES = 1000;

    @BeforeEach
    void setUp() {
        arrayList.clear();
        vavrList = io.vavr.collection.List.of();
        javaExecutionList.add(new Execution("insert head", headList, arrayList, TIMES));
        javaExecutionList.add(new Execution("insert middle", twoParamsList, arrayList, TIMES));
        javaExecutionList.add(new Execution("insert tail", twoParamsList, arrayList, TIMES));
        javaExecutionList.add(new Execution("remove head", headList, arrayList, TIMES));
        javaExecutionList.add(new Execution("remove middle", headList, arrayList, TIMES));
        javaExecutionList.add(new Execution("remove tail", headList, arrayList, TIMES));
        vavrExecutionList.add(new Execution("insert head", headList, vavrList, TIMES));
        vavrExecutionList.add(new Execution("insert middle", twoParamsList, vavrList, TIMES));
        vavrExecutionList.add(new Execution("insert tail", twoParamsList, vavrList, TIMES));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testInsertPerformance() throws InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < 3; i++) {
            arrayList.clear();
            List<Integer> arrayListResult = (List<Integer>) javaExecutionList.get(i).execute();
            io.vavr.collection.List<Integer> vavrListResult = (io.vavr.collection.List<Integer>) vavrExecutionList.get(i).execute();
            assertEquals(arrayListResult.size(), TIMES);
            assertEquals(vavrListResult.length(), TIMES);
        }
    }

    @Test
    void testRemovePerformance() throws InvocationTargetException, IllegalAccessException {
        for (long l = 0; l < TIMES; l++) {
            vavrList = vavrList.append(1);
        }
        vavrExecutionList.add(new Execution("remove head", headList, vavrList, TIMES));
        vavrExecutionList.add(new Execution("remove middle", headList, vavrList, TIMES));
        vavrExecutionList.add(new Execution("remove tail", headList, vavrList, TIMES));
        for (int i = 3; i < 6; i++) {
            for (long l = 0; l < TIMES; l++) {
                arrayList.add(1);
            }
            List<Integer> arrayListResult = (List<Integer>) javaExecutionList.get(i).execute();
            io.vavr.collection.List<Integer> vavrListResult = (io.vavr.collection.List<Integer>) vavrExecutionList.get(i).execute();
            assertEquals(arrayListResult.size(), 0);
            assertEquals(vavrListResult.length(), 0);
        }
    }


}