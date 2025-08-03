# compose-validator

A tiny, expressive validation DSL for Java. 
Because writing if statements everywhere drains you lifeforce faster
than a mindflayer can suck your brain dry

## Why?

You’ve got data models. 
You’ve got constraints. 

You could spread *null* checks and *if*s, 
nest them like rusian dolls 
and barf them all over your codebase.

Or… 

you could declare intent like a civilized being:

```java
Validator<Customer> validator = Validator.of(Customer.class)
    .field(Customer::getName, notBlank("Name required"))
    .field(Customer::getAge, min(18, "Must be at least 18"))
    .when(Customer::getEmail, email -> !email.isBlank(),
        field(Customer::getEmail, validEmail("Invalid email")))
    .field(Customer::getAddress, nested(addressValidator));
```

You're welcome
