package Validators;

import Exceptions.ValidationException;

/**
 *
 * @param <E>
 */
public interface Validator<E> {

    /**
     * @param entity
     * @throws ValidationException
     */
    void validate(E entity); /*throws ValidationException;*/
}