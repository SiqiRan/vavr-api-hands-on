package org.dataStructures;

import org.experiment.ListPerformanceExperiment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListPerformanceExperimentTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGenerateList() {
        ListPerformanceExperiment listExperiment = new ListPerformanceExperiment();
        listExperiment.performance();
    }
}