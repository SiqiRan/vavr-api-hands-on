package org.learning;

import io.vavr.*;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class VavrFunctionTest {
    //Method definition
    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

    //The anonymous class definition path
    Function2<Integer, Integer, Integer> sumByAnonymousClass = new Function2<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer a, Integer b) {
            return a + b;
        }
    };


    //Composition

    Function1<Integer, Integer> plusOne = a -> a + 1;
    Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
    Function1<Integer, Integer> plusOneThenMultiplyByTwo = multiplyByTwo.compose(plusOne);

    @Test
    void should_return_multiplying_after_plus_one() {
        assertEquals(plusOneThenMultiplyByTwo.apply(2), 6);
    }

    //Lifting

    Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
    Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

    @Test
    void should_return_none_and_some_result() {
        assertInstanceOf(Option.None.class, safeDivide.apply(1, 0));
        assertEquals(Option.some(2), safeDivide.apply(4, 2));
    }

    //Partial Application

    Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> sumBySixNumbers = (a, b, c, d, e, f) -> a + b + c + d + e + f;
    Function5<Integer, Integer, Integer, Integer, Integer, Integer> addOne = sumBySixNumbers.apply(1);
    Function4<Integer, Integer, Integer, Integer, Integer> addThree = addOne.apply(2);
    Function1<Integer, Integer> addTen = addThree.apply(2, 3, 2);

    @Test
    void should_return_apply_parameters_one_by_one() {
        assertEquals(15, addTen.apply(5));
    }

    //Currying

    Function1<Integer, Integer> addTwoForTwoParams = sum.curried().apply(2);
    Function3<Integer, Integer, Integer, Integer> sumByThree = (a, b, c) -> a + b + c;
    Function1<Integer, Function1<Integer, Integer>> addTwoForThreeParams = sumByThree.curried().apply(2);

    @Test
    void should_return_curried_applied_answer() {
        assertEquals(addTwoForThreeParams.apply(2).apply(2), 6);
    }

    //Memoization

    Function0<Double> hashCache = Function0.of(Math::random).memoized();

    @Test
    void should_have_random_value_stored() {
        double randomValue = hashCache.apply();
        double couldBeRandomValue = hashCache.apply();
        assertEquals(randomValue, couldBeRandomValue);
    }
}
