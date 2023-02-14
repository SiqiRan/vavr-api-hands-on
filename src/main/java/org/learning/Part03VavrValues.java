package org.learning;

import io.vavr.Function1;
import io.vavr.Lazy;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.util.Optional;

public class Part03VavrValues {

    static Option<String> maybeFooVavr = Option.of("foo");

    private Part03VavrValues() {

    }


    public static Optional<String> maybeFoo() {
        //todo: return an optional object containing a string "foo" using java's api.
        return null;
    }

    public static Boolean isMaybeFooBarOptinalPresent() {
        Optional<String> maybeFoo = Optional.of("foo");
        Optional<String> maybeFooBar = maybeFoo.map(item -> (String) null).map(String::toUpperCase);
        //todo: return true/false to determine if the maybeFooBar optional is present.
        return null;
    }

    public static Integer willVavrOptionMapThrowException() {
        //todo: change the return value of expected path(try or catch) to 1 to pass.
        try {
            Option<String> maybeNullVavr = Option.of(null);
            maybeNullVavr.map(String::toUpperCase);
            maybeFooVavr.map(s -> (String) null).map(s -> s.toUpperCase() + "bar");
            return 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public static Integer willVavrOptionFlatmapThrowException() {
        //todo: change the return value of expected path(try or catch) to 1 to pass.
        try {
            Option<String> maybeFooBarVavr = maybeFooVavr.map(s -> (String) null)
                    .flatMap(Option::of)
                    .map(s -> s.toUpperCase() + "bar");
            return 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public static Integer willVavrOptionFlatmapWithTransformationThrowException() {
        //todo: change the return value of expected path(try or catch) to 1 to pass.
        try {
            Option<String> maybeFooBarVavr = maybeFooVavr.flatMap(s -> Option.of((String) null))
                    .map(s -> s.toUpperCase() + "bar");
            return 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public static Try<Integer> tryToDoRiskyWork() {
        Function1<Integer, Integer> workMayThrowException = (a) -> 2 / a;
        //todo: calculate 2/0 without throw any exception.
        //use Try to do this.
        return null;
    }


    public static Lazy<Double> lazyRandom() {
        //todo: return Lazy object to generate a random number.
        //use Lazy to do this. And be sure to use Math.random() instead of hard-coded number.
        return null;
    }


    public static Either<String, Integer> eitherGrades(Integer rawMark) {
        //todo: return a Either object for grading.
        //when the raw mark is greater than (including equal to) 80, should return the mark.
        //when the raw makr is lower than 80, should return a String: sorry, you didn't get a high distinction score.
        //use Either to do this.
        return null;
    }
}
