package ru.study.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.study.model.Employee;
import ru.study.validation.fieldsExtectors.Extractor;
import ru.study.validation.fieldsExtectors.FirstNameEmployeeExtractorImpl;
import ru.study.validation.fieldsExtectors.LastNameEmployeeExtractorImpl;
import ru.study.validation.fieldsExtectors.RoleEmployeeExtractorImpl;
import ru.study.validation.fieldsValidators.FirstNameEmployeeValidator;
import ru.study.validation.fieldsValidators.LastNameEmployeeValidator;
import ru.study.validation.fieldsValidators.RoleEmployeeValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class EmployeeTest {

    private static final String ERROR_FIRSTNAME = "First name must not be empty, contains only letters, and be from 2 to 23 characters long";
    private static final String ERROR_LASTNAME = "Last name must not be empty, contains only letters, and be from 2 to 23 characters long";
    private static final String ERROR_ROLE = "Role must not be empty, contains only letters, and be from 2 to 23 characters long";

    private Employee correctEmp;
    private ValidatorImpl<Employee, String> ev;

    @BeforeEach
    void setUp() {
        this.correctEmp = new Employee(1, "Ivan", "Ivanovich", "Java developer");
        Map<Extractor<Employee, String>, Validator<String>> validators = new HashMap<>();
        validators.put(new FirstNameEmployeeExtractorImpl(), new FirstNameEmployeeValidator());
        validators.put(new LastNameEmployeeExtractorImpl(), new LastNameEmployeeValidator());
        validators.put(new RoleEmployeeExtractorImpl(), new RoleEmployeeValidator());
        this.ev = new ValidatorImpl<>(validators);
    }

    @Test
    @DisplayName("Проверка валидации корректного сотрудника")
    void validateCorrectEmp() {
        ValidationResult vr = ev.validate(correctEmp);
        Assertions.assertThat(vr.isValid())
                .isNotNull()
                .isTrue();
        Assertions.assertThat(vr.getMessages())
                .isNotNull()
                .isEmpty();
    }

    @ParameterizedTest
    @DisplayName("Проверка валидации некорректного сотрудника")
    @MethodSource("getInvalidEmployeesData")
    void validateIncorrectEmp(String firstName, String lastName, String role) {
        final Employee invalidEmployee = new Employee(0, firstName, lastName, role);
        ValidationResult vr = ev.validate(invalidEmployee);
        Assertions.assertThat(vr.isValid())
                .isNotNull()
                .isFalse();
        Assertions.assertThat(vr.getMessages())
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .contains(ERROR_FIRSTNAME)
                .contains(ERROR_LASTNAME)
                .contains(ERROR_ROLE)

        ;
    }

    static Stream<Arguments> getInvalidEmployeesData() {
        return Stream.of(
                arguments("", "", ""),
                arguments("A", "A", "A"),
                arguments("AB", "AB", "AB"),
                arguments("1", "2", "3"),
                arguments("123", "234", "345"),
                arguments("qwertyuiop[]asdfghjkl;'zc",
                        "qwertyuiop[]asdfghjkl;'zc", "qwertyuiop[]asdfghjkl;'zc")
        );
    }
}