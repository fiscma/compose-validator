package org.fiscma.composevalidator.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Martin Fischer
 *
 * This class encapsulates information about occurring errors during validation
 *
 */
public class ValidationResult {
    private List<String> errors = new ArrayList<>();

    /**
     * adds a validation error
     * @param error the validation error
     */
    public void addError(String error) {
        errors.add(error);
    }

    /**
     * Gives information about validity
     * @return true if valid
     */
    public boolean isValid() {
        return errors.isEmpty();
    }

    /**
     *
     * @return the validation errors
     */
    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    /**
     * Merges the validation errors of a composed validation into one single result
     * @param results The validation results
     * @return the merged validation result
     */
    public static ValidationResult mergedResult(ValidationResult... results) {
        ValidationResult mergedResult = new ValidationResult();
        for (ValidationResult result : results) {
            mergedResult.errors.addAll(result.getErrors());
        }
        return mergedResult;
    }
}
