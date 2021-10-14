package ru.study.validation.fieldsExtectors;

public interface Extractor<E> {
    String getValueFromFieldModel(E e) throws RuntimeException;
}
