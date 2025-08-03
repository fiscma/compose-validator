package org.fiscma.composevalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * @author Martin Fischer
 * Base validation class
 *
 * @param <T> the object that will be validated
 *
 */
public class Validator<T> {
    private final List<ValidationRule<T, ?>> rules = new ArrayList<>();

    public static <T> Validator<T> of() {
        return new Validator<T>();
    }

    public <V> Validator<T> field(Function<T, V> extractor, Predicate<V> predicate, String error) {
        rules.add(new ValidationRule<>(extractor, predicate, error));
        return this;
    }

    public <V> Validator<T> field(Function<T, V> extractor, Validator<V> nestedValidator) {
        rules.add(new NestedValidationRule<>(extractor, nestedValidator));
        return this;
    }

    public ValidationResult validate(T target) {
        ValidationResult result = new ValidationResult();
        for (ValidationRule<T, ?> rule : rules) {
            rule.validate(target, result);
        }
        return result;
    }
}
