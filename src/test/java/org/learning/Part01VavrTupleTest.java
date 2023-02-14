package org.learning;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Part01VavrTupleTest {
    Tuple3<String, String, String> testTupleCreation = Part01VavrTuple.createTuple();

    @Test
    void should_return_tuple_Results() {
        assertEquals(testTupleCreation._1, "I");
        assertEquals(testTupleCreation._2, "Love");
        assertEquals(testTupleCreation._3, "Coding");
    }

    Tuple2<String, Integer> java8 = Tuple.of("java", 8);

    Tuple2<String, Integer> transformComponentWise = Part01VavrTuple.transformComponentWise();

    @Test
    void should_return_transformed_result() {
        assertEquals(transformComponentWise._1, "va");
        assertEquals(transformComponentWise._2, 1);
    }

    Tuple2<String, Integer> transformUsingMapper = Part01VavrTuple.transformUsingMapper();

    @Test
    void should_return_same_result() {
        assertEquals(transformUsingMapper._1, "vavr");
        assertEquals(transformUsingMapper._2, 1);
    }

    String transformATupleToString = Part01VavrTuple.transformToString();

    @Test
    void should_return_string_from_tuple() {
        assertEquals(transformATupleToString, "vavr 1");
    }

}
