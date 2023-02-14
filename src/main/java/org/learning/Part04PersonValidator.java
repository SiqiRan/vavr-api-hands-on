package org.learning;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public class Part04PersonValidator {
    private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
    private static final int MIN_AGE = 0;

    public Validation<Seq<String>, Person> validatePerson(String name, int age) {
        return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
    }

    private Validation<String, String> validateName(String name) {
        //todo: find a way to check if the name is completely made of valid characters.
        //should return a Validation object.
        return null;
    }

    private Validation<String, Integer> validateAge(int age) {
        //todo: find a way to check if the age is valid( greater than or equal to 0).
        //should return a Validation object.
        return null;
    }
}
