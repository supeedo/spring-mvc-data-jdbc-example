package ru.study.model;

import com.opencsv.bean.CsvBindByName;
import ru.study.validation.ValidationResult;
import ru.study.validation.Validator;

import java.util.Objects;

public class Employee implements Model, Validator<Employee> {
    private static final String ERROR = " must not be empty, contains only letters, and be from 2 to 23 characters long";

    @CsvBindByName(column = "id")
    private long id;

    @CsvBindByName(column = "firstName")
    private String firstName;

    @CsvBindByName(column = "lastName")
    private String lastName;

    @CsvBindByName(column = "role")
    private String role;

    public Employee() {
    }

    public Employee(long id, String firstName, String lastName, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee that = (Employee) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role);
    }

    @Override
    public ValidationResult validate(Employee employee) {
        String namePattern = "^([А-ЯA-Z]{1}[а-яёa-z]{1,23})$|^([А-ЯA-Z]{1}[а-яёa-z]{1,23})+\\s([А-ЯA-Zа-яёa-z]{1}[а-яёa-z]{1,23})$";
        final ValidationResult vr = new ValidationResult();
        if (!employee.getFirstName().matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("First Name" + ERROR);
        }
        if (!employee.getLastName().matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("Last Name" + ERROR);
        }
        if (!employee.getRole().matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("Role" + ERROR);
        }
        return vr;
    }
}
