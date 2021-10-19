package ru.study.validation.fieldsExtectors;


import java.util.Optional;

public interface Extractor<E, T> {
    Optional<T> getValueFromFieldModel(E e) throws RuntimeException;
}
