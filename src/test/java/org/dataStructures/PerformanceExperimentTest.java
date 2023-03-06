package org.dataStructures;

import org.experiment.Execution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PerformanceExperimentTest {

    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();
    io.vavr.collection.List<Integer> vavrList = io.vavr.collection.List.of();
    ArrayList<Object> headList = new ArrayList<>(Collections.singletonList(0));
    ArrayList<Object> twoParamsList = new ArrayList<>(Arrays.asList(0, 1));
    List<Execution> arrayExecutionList = new ArrayList<>();
    List<Execution> linkedExecutionList = new LinkedList<>();
    List<Execution> vavrExecutionList = new ArrayList<>();

    long TIMES = 50000;

    @BeforeEach
    void setUp() {
        clearAllDataStructure();
        arrayExecutionList.clear();
        vavrExecutionList.clear();
    }

    private void clearAllDataStructure() {
        linkedList.clear();
        arrayList.clear();
        vavrList = io.vavr.collection.List.of();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testInsertPerformance() throws InvocationTargetException, IllegalAccessException {
        arrayExecutionList.add(new Execution("insert head", twoParamsList, arrayList, TIMES));
        arrayExecutionList.add(new Execution("insert middle", twoParamsList, arrayList, TIMES));
        arrayExecutionList.add(new Execution("insert tail", headList, arrayList, TIMES));
        linkedExecutionList.add(new Execution("insert head", twoParamsList, linkedList, TIMES));
        linkedExecutionList.add(new Execution("insert middle", twoParamsList, linkedList, TIMES));
        linkedExecutionList.add(new Execution("insert tail", headList, linkedList, TIMES));
        vavrExecutionList.add(new Execution("insert head", headList, vavrList, TIMES));
        vavrExecutionList.add(new Execution("insert middle", twoParamsList, vavrList, TIMES));
        vavrExecutionList.add(new Execution("insert tail", headList, vavrList, TIMES));
        for (int i = 0; i < 3; i++) {
            arrayList.clear();
            linkedList.clear();
            List<Integer> linkedListResult = (List<Integer>) linkedExecutionList.get(i).execute();
            List<Integer> arrayListResult = (List<Integer>) arrayExecutionList.get(i).execute();
            io.vavr.collection.List<Integer> vavrListResult = (io.vavr.collection.List<Integer>) vavrExecutionList.get(i).execute();
            assertEquals(linkedListResult.size(), TIMES);
            assertEquals(arrayListResult.size(), TIMES);
            assertEquals(vavrListResult.length(), TIMES);
        }
    }

    @Test
    void testRemovePerformance() throws InvocationTargetException, IllegalAccessException {
        for (long l = 0; l < TIMES; l++) {
            vavrList = vavrList.append(1);
        }
        arrayExecutionList.add(new Execution("remove head", twoParamsList, arrayList, TIMES));
        arrayExecutionList.add(new Execution("remove middle", twoParamsList, arrayList, TIMES));
        arrayExecutionList.add(new Execution("remove tail", headList, arrayList, TIMES));
        linkedExecutionList.add(new Execution("remove head", twoParamsList, linkedList, TIMES));
        linkedExecutionList.add(new Execution("remove middle", twoParamsList, linkedList, TIMES));
        linkedExecutionList.add(new Execution("remove tail", headList, linkedList, TIMES));
        vavrExecutionList.add(new Execution("remove head", headList, vavrList, TIMES));
        vavrExecutionList.add(new Execution("remove middle", headList, vavrList, TIMES));
        vavrExecutionList.add(new Execution("remove tail", headList, vavrList, TIMES));
        for (int i = 0; i < 3; i++) {
            for (long l = 0; l < TIMES; l++) {
                arrayList.add(1);
            }
            for (long l = 0; l < TIMES; l++) {
                linkedList.add(1);
            }
            List<Integer> linkedListResult = (List<Integer>) linkedExecutionList.get(i).execute();
            List<Integer> arrayListResult = (List<Integer>) arrayExecutionList.get(i).execute();
            io.vavr.collection.List<Integer> vavrListResult = (io.vavr.collection.List<Integer>) vavrExecutionList.get(i).execute();
            assertEquals(arrayListResult.size(), 0);
            assertEquals(vavrListResult.length(), 0);
            assertEquals(linkedListResult.size(), 0);
        }
    }


    @Test
    void testQueryPerformance() throws InvocationTargetException, IllegalAccessException {
        for (long i = 0; i < TIMES; i++) {
            vavrList = vavrList.append(1);
        }
        for (long l = 0; l < TIMES; l++) {
            arrayList.add(1);
        }
        new Execution("get", twoParamsList, arrayList, TIMES).execute();
        new Execution("get", twoParamsList, vavrList, TIMES).execute();
    }

    @Test
    void testModifyPerformance() throws InvocationTargetException, IllegalAccessException {
        for (long i = 0; i < TIMES; i++) {
            vavrList = vavrList.append(1);
        }
        for (long l = 0; l < TIMES; l++) {
            arrayList.add(1);
        }
        new Execution("modify", twoParamsList, arrayList, TIMES).execute();
        new Execution("modify", twoParamsList, vavrList, TIMES).execute();
    }

}