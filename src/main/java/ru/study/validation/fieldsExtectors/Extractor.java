package ru.study.validation.fieldsExtectors;

public interface Extractor<E> {

    String getValueFromFieldModelByFieldName(String fieldName, E e) throws RuntimeException;
}
