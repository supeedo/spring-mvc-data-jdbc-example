package ru.study.model;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Employee {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String employeeRoleId;

    public Employee() {
    }

    public Employee(long id, String firstName, String lastName, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeRoleId = role;
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

    public String getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmployeeRoleId(String employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + employeeRoleId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee that = (Employee) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(employeeRoleId, that.employeeRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, employeeRoleId);
    }

}
