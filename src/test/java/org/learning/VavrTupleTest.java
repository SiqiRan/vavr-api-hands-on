package org.learning;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VavrTupleTest {
    //todo
    Tuple3<String, String, String> testTupleCreation = Tuple.of("I", "Love", "Coding");

    @Test
    void should_return_tuple_Results() {
        assertEquals(testTupleCreation._1, "I");
        assertEquals(testTupleCreation._2, "Love");
        assertEquals(testTupleCreation._3, "Coding");
    }

    Tuple2<String, Integer> java8 = Tuple.of("java", 8);

    //todo
    Tuple2<String, Integer> transformComponentWise = java8.map(s -> s.substring(2), i -> i / 8);

    @Test
    void should_return_transformed_result() {
        assertEquals(transformComponentWise._1, "va");
        assertEquals(transformComponentWise._2, 1);
    }

    //
    Tuple2<String, Integer> transformUsingMapper = java8.map(
            (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
    );

    @Test
    void should_return_same_result() {
        assertEquals(transformUsingMapper._1, "vavr");
        assertEquals(transformUsingMapper._2, 1);
    }

    String transformATupleToString = java8.apply((s, i) -> s.substring(2) + "vr " + i / 8);

    @Test
    void should_return_string_from_tuple() {
        assertEquals(transformATupleToString, "vavr 1");
    }


}
