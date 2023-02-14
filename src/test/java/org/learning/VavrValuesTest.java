package org.learning;

import io.vavr.Lazy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VavrValuesTest {

    @Test
    void should_get_foo() {
        assertEquals(VavrValues.maybeFoo().get(), "foo");
    }

    @Test
    void should_not_be_present() {
        assertNotEquals(Boolean.TRUE, VavrValues.isMaybeFooBarOptinalPresent());
    }

    @Test
    void should_throw_null_pointer_exception() {
        assertEquals(1, VavrValues.willVavrOptionMapThrowException());
    }

    @Test
    void should_not_throw_null_pointer_using_flatmap() {
        assertEquals(1, VavrValues.willVavrOptionFlatmapThrowException());
    }

    @Test
    void should_not_throw_null_pointer_using_flat_map_with_transformation() {
        assertEquals(1, VavrValues.willVavrOptionFlatmapWithTransformationThrowException());
    }

    @Test
    void should_not_throw_exception() {
        assertFalse(VavrValues.tryToDoRiskyWork().isFailure());
    }

    @Test
    void should_memorize_computed_value() {
        Lazy<Double> lazy = VavrValues.lazyRandom();
        assertFalse(lazy.isEvaluated());
        Double firstCalcResult = lazy.get();
        assertTrue(lazy.isEvaluated());
        Double secondCalcResult = lazy.get();
        assertEquals(firstCalcResult, secondCalcResult);
    }

    @Test
    void should_get_the_message_or_integer_correctly() {
        assertEquals(VavrValues.eitherGrades(76).getLeft(), "sorry, you didn't get a high distinction score.");
        assertEquals(VavrValues.eitherGrades(85).get(), 85);
        assertEquals(VavrValues.eitherGrades(80).map(i -> i / 2).get(), 40);
    }

    @Test
    void should_validate_person_correctly() {
        PersonValidator personValidator = new PersonValidator();
        assertTrue(personValidator.validatePerson("Arthur", 36).isValid());
        assertFalse(personValidator.validatePerson("Pearson", -1).isValid());
    }

}


