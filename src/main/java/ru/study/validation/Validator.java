package ru.study.validation;

public interface Validator<E>{
    ValidationResult validate(E e);

}
