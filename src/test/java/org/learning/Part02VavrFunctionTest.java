package org.learning;

import io.vavr.*;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class Part02VavrFunctionTest {
    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

    @Test
    void should_return_multiplying_after_plus_one() {
        assertEquals(Part02VavrFunction.composition(), 6);
    }

    @Test
    void should_return_none_and_some_result() {
        assertInstanceOf(Option.None.class, Part02VavrFunction.safeDivide().apply(2, 0));
        assertEquals(Option.some(2), Part02VavrFunction.safeDivide().apply(4, 2));
    }

    //Partial Application

    @Test
    void should_return_apply_parameters_one_by_one() {
        assertEquals(15, Part02VavrFunction.addTen().apply(5));
    }

    @Test
    void should_return_curried_applied_answer() {
        assertEquals(Part02VavrFunction.addCurriedFunction().apply(2).apply(2), 6);
    }

    //Memoization

    Function0<Double> hashCache = Part02VavrFunction.memoization();

    @Test
    void should_have_random_value_stored() {
        double randomValue = hashCache.apply();
        double couldBeRandomValue = hashCache.apply();
        assertEquals(randomValue, couldBeRandomValue);
    }
}
