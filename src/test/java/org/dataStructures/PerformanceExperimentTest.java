package org.dataStructures;

import org.experiment.Execution;
import org.experiment.PerformanceExperiment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PerformanceExperimentTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFunctionalities() throws InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Execution execution = new Execution("insert head", new ArrayList<>(Arrays.asList(1)), arrayList, 1000);
        execution.execute();
    }
}