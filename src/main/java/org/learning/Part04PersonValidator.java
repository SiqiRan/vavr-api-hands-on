package org.learning;

import io.vavr.collection.CharSeq;
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
        return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                ? Validation.valid(name)
                : Validation.invalid("Name contains invalid characters: '"
                + seq.distinct().sorted() + "'"));
    }

    private Validation<String, Integer> validateAge(int age) {
        //todo: find a way to check if the age is valid( greater than or equal to 0).
        //should return a Validation object.
        return age < MIN_AGE
                ? Validation.invalid("Age must be at least " + MIN_AGE)
                : Validation.valid(age);
    }
}
