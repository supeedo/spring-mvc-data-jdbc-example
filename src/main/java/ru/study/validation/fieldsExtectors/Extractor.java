package ru.study.validation.fieldsExtectors;

import java.util.Optional;

public interface Extractor<E> {
    Optional<?> getValueFromFieldModel(E e) throws RuntimeException;
}
