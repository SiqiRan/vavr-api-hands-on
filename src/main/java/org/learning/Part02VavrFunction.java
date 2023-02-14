package org.learning;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Option;

public class Part02VavrFunction {

    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

    private Part02VavrFunction() {

    }

    public static Function1<Integer, Integer> composition() {
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        /* todo: use plusOne and multiplyByTwo to compose a new function which can add one to the input
        then multiply by two as return value.
        use compose() to a finish this.*/
        return multiplyByTwo.compose(plusOne);
    }


    public static Function2<Integer, Integer, Option<Integer>> safeDivide() {
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        /* todo: convert the initial divide function to a total function which will accept infeasible parameters
        and return None or Some according to the input.
        use lift() to a finish this.*/
        return Function2.lift(divide);
    }


    public static Function1<Integer, Integer> addTen() {
        Function3<Integer, Integer, Integer, Integer> sumByThreeNumbers = (a, b, c) -> a + b + c;
        /* todo: convert the sumByThreeNumbers function to a function which accepts one parameter and behave
        as adding ten to the input.
        use apply() to do this.*/
        return sumByThreeNumbers.apply(5, 5);
    }

    public static Function1<Integer, Function1<Integer, Integer>> addCurriedFunction() {
        Function3<Integer, Integer, Integer, Integer> addByTwoCurriedFunction = (a, b, c) -> a + b + c;
        /* todo: convert the sumByThreeNumbers function to a function which will accept one parameter and
        add two to it then return a function which will also accept one parameter then return a integer.
        use curried() and apply() to do this.*/
        return addByTwoCurriedFunction.curried().apply(2);
    }

    public static Function0<Double> memoization() {
        Function0<Double> randomNumber = () -> Math.random();
        /* todo: convert this random number function to function which will give the exactly same answer
        for every apply.
        use memoized() to do this.*/
        return randomNumber.memoized();
    }
}
