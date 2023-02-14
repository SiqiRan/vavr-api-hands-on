package org.learning;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;

public class VavrTuple {
    private VavrTuple() {

    }

    final static Tuple2<String, Integer> java8 = Tuple.of("java", 8);

    public static Tuple3<String, String, String> createTuple() {
        //todo: create a tuple containing three String: I, Love, Coding.
        return null;
    }

    public static Tuple2<String, Integer> transformComponentWise() {
        //todo: use component wise to transform java8 tuple to a tuple containing two elements: va 1.
        return java8;
    }


    public static Tuple2<String, Integer> transformUsingMapper() {
        //todo: use mapper to transform java8 tuple to a tuple containing two elements: vavr 1.
        return java8;
    }

    public static String transformToString() {
        //todo: transform java8 tuple to a string: vavr 1.
        return "";
    }
}
