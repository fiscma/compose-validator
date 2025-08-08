package org.fiscma.composevalidator.base;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Martin Fischer
 */
public class ValidationRule<T, V> {
    final Function<T, V> extractor;
    private final Predicate<V> predicate;
    private final String errorMessage;

    public ValidationRule(Function<T, V> extractor, Predicate<V> predicate, String errorMessage) {
        this.extractor = extractor;
        this.predicate = predicate;
        this.errorMessage = errorMessage;
    }

    public void validate(T target, ValidationResult result) {
        V value = extractor.apply(target);
        if (!predicate.test(value)) {
            result.addError(errorMessage);
        }
    }
}
