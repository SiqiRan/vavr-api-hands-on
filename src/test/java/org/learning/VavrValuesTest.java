package org.learning;

import io.vavr.Function1;
import io.vavr.Lazy;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

public class VavrValuesTest {
    //use java's optional version

    Optional<String> maybeFoo = Optional.of("foo");

    @Test
    void should_get_foo() {
        assertEquals(maybeFoo.get(), "foo");
    }

    Optional<String> maybeFooBar = maybeFoo.map(s -> (String) null).map(String::toUpperCase);

    @Test
    void should_not_be_present() {
        assertFalse(maybeFooBar.isPresent());
    }

    //use vavr's option version

    Option<String> maybeFooVavr = Option.of("foo");

    @Test
    void should_throw_null_pointer_exception() {
        try {
            //if the Option is defined as None at the first place, it won't trigger NullPointer exception later on.
            Option<String> maybeNullVavr = Option.of(null);
            maybeNullVavr.map(String::toUpperCase);
            //This what will really throw exception. Stick to its primitive type.
            maybeFooVavr.map(s -> (String) null).map(s -> s.toUpperCase() + "bar");
            fail();
        } catch (NullPointerException e) {
            //lead to success
        }
    }

    //use flat map to deal with null problems.

    @Test
    void should_not_throw_null_pointer_using_flatmap() {
        try {
            Option<String> maybeFooBarVavr = maybeFooVavr.map(s -> (String) null)
                    .flatMap(Option::of)
                    .map(s -> s.toUpperCase() + "bar");
            assertTrue(maybeFooBarVavr.isEmpty());
        } catch (NullPointerException e) {
            fail();
        }
    }

    @Test
    void should_not_throw_null_pointer_using_flat_map_with_transformation() {
        try {
            Option<String> maybeFooBarVavr = maybeFooVavr.flatMap(s -> Option.of((String) null))
                    .map(s -> s.toUpperCase() + "bar");
            assertTrue(maybeFooBarVavr.isEmpty());
        } catch (NullPointerException e) {
            fail();
        }
    }

    Function1<Integer, Integer> workThrowException = (a) -> 2 / a;
    Function1<List<Integer>, Integer> workThrowExceptionToo = (a) -> a.get(2);
    Function1<Integer, Integer> workWithoutException = (a) -> 4 / a;

    @Test
    void should_not_throw_exception() {
        Try.of(() -> workThrowException.apply(0)).recover(x -> Match(x).
                of(
                        Case($(instanceOf(ArithmeticException.class)), t -> workThrowExceptionToo.apply(new ArrayList<>()))));
    }

    //use lazy to evaluate a lazy value
    CharSequence lazyChars = Lazy.val(() -> "Good", CharSequence.class);

    @Test
    void should_memorize_computed_value() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        assertFalse(lazy.isEvaluated());
        Double firstCalcResult = lazy.get();
        assertTrue(lazy.isEvaluated());
        Double secondCalcResult = lazy.get();
        assertEquals(firstCalcResult, secondCalcResult);
    }

    private Either<String, Integer> computeWithEither(Integer input) {
        if (input >= 80) {
            return Either.right(input);
        } else {
            return Either.left("sorry, you didn't get a high distinction score");
        }
    }

    @Test
    void should_get_the_message_or_integer_correctly() {
        assertEquals(computeWithEither(78).getLeft(), "sorry, you didn't get a high distinction score");
        assertEquals(computeWithEither(85).get(), 85);
        assertEquals(computeWithEither(80).map(i -> i / 2).get(), 40);
    }

    @Test
    void should_validate_person_correctly() {
        PersonValidator personValidator = new PersonValidator();
        assertTrue(personValidator.validatePerson("Arthur", 36).isValid());
        assertFalse(personValidator.validatePerson("Pearson", -1).isValid());
    }

}


