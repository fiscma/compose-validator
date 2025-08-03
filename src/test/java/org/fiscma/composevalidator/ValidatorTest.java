package org.fiscma.composevalidator;

import org.fiscma.composevalidator.testentities.Address;
import org.fiscma.composevalidator.testentities.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {

    @Test
    void testCustomerValidation() {
        Validator<Address> addressValidator = Validator.<Address>of()
                .field(Address::getStreet, street -> street != null && !street.isBlank(), "City must not be blank");

        Validator<Customer> customerValidator = Validator.<Customer>of()
                .field(Customer::getName, name -> name != null && !name.isBlank(), "Name must not be blank")
                .field(Customer::getAddress, addressValidator);

        Customer invalidCustomer = new Customer("", new Address(""));
        ValidationResult result = customerValidator.validate(invalidCustomer);

        assertFalse(result.isValid());
        assertEquals(2, result.getErrors().size());
        result.getErrors().forEach(System.out::println);
    }
}
