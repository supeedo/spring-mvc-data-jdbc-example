package ru.study.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.study.validation.ValidationResult;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class EmployeeTest {

    private static final String ERROR_FIRSTNAME = "First Name must not be empty, contains only letters, and be from 2 to 23 characters long";
    private static final String ERROR_LASTNAME = "Last Name must not be empty, contains only letters, and be from 2 to 23 characters long";
    private static final String ERROR_ROLE = "Role must not be empty, contains only letters, and be from 2 to 23 characters long";

    private Employee correctEmp;

    @BeforeEach
    void setUp() {
        this.correctEmp = new Employee(1, "Ivan", "Ivanovich", "Java developer");
    }

    @Test
    @DisplayName("Проверка валидации корректного сотрудника")
    void validateCorrectEmp() {
        ValidationResult vr = correctEmp.validate(correctEmp);
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
        ValidationResult vr = invalidEmployee.validate(invalidEmployee);
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
                arguments("qwertyuiop[]asdfghjkl;'zc", "qwertyuiop[]asdfghjkl;'zc", "qwertyuiop[]asdfghjkl;'zc")
        );
    }
}