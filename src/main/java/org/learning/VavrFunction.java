package org.learning;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Option;

public class VavrFunction {

    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
    Function1<Integer, Integer> plusOne = a -> a + 1;
    Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
    Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

    private VavrFunction() {

    }

    public static Integer composition() {
        /* todo: use plusOne and multiplyByTwo to compose a new function which can add one to the input
        then multiply by two as return value.
        use compose() to a finish this.*/
        return 0;
    }


    public static Function2<Integer, Integer, Option<Integer>> safeDivide() {
        /* todo: convert the initial divide function to a total function which will accept infeasible parameters
        and return None or Some according to the input.
        use lift() to a finish this.*/
        return null;
    }


    public static Function1<Integer, Integer> addTen() {

        Function3<Integer, Integer, Integer, Integer> sumByThreeNumbers = (a, b, c) -> a + b + c;
        /* todo: convert the sumByThreeNumbers function to a function which accepts one parameter and behave
        as adding ten to the input.
        use apply() to do this.*/
        return null;
    }

    public static Function1<Integer, Function1<Integer, Integer>> addCurriedFunction() {
        Function3<Integer, Integer, Integer, Integer> sumByThreeNumbers = (a, b, c) -> a + b + c;
        /* todo: convert the sumByThreeNumbers function to a function which will accept one parameter and
        return a function which will also accept one parameter then return a integer.
        use curried() and apply() to do this.*/
        return null;
    }

    public static Function0<Double> memoization() {
        Function0<Double> randomNumber = () -> Math.random();
        /* todo: convert this random number function to function which will give the exactly same answer
        for every apply.
        use memoized() to do this.*/
        return randomNumber;
    }
}
