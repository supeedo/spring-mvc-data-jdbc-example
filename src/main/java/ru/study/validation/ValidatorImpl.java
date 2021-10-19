package ru.study.validation;

import ru.study.exceptions.ModelException;
import ru.study.validation.fieldsExtectors.Extractor;

import java.util.HashMap;
import java.util.Map;

import static ru.study.exceptions.ModelException.ErrorCode.GETTING_DATA_FROM_MODEL_ERROR;

public class ValidatorImpl<E, R> implements Validator<E> {
    private final ValidationResult validResult;
    private final Map<Extractor<E, R>, Validator<R>> validators = new HashMap<>();

    public ValidatorImpl(Map<Extractor<E, R>, Validator<R>> validatorMap) {
        this.validResult = new ValidationResult();
        this.validators.putAll(validatorMap);
    }

    @Override
    public ValidationResult validate(E model) throws RuntimeException {
        for (Map.Entry<Extractor<E, R>, Validator<R>> entry : validators.entrySet()) {
            R r = entry.getKey().getValueFromFieldModel(model).orElseThrow(() -> new ModelException("Error extract data from model", GETTING_DATA_FROM_MODEL_ERROR));
            ValidationResult vr = entry.getValue().validate(r);
            validResult.merge(vr);
        }
        return validResult;
    }
}
