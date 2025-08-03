package org.fiscma.composevalidator;

import java.util.function.Function;

/**
 * @author Martin Fischer
 * @param <T>
 * @param <V>
 */
public class NestedValidationRule<T, V> extends ValidationRule<T, V> {
    private final Validator<V> nestedValidator;

    public NestedValidationRule(Function<T, V> extractor, Validator<V> nestedValidator) {
        super(extractor, v -> true, "");
        this.nestedValidator = nestedValidator;
    }

    @Override
    public void validate(T target, ValidationResult result) {
        V value = super.extractor.apply(target);
        if (value != null) {
            ValidationResult nestedResult = nestedValidator.validate(value);
            for (String error : nestedResult.getErrors()) {
                result.addError(error);
            }
        }
    }
}
