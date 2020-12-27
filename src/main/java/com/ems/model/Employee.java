package com.ems.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

@ApiModel(description = "Class representing a employee in the application")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Unique identifier of the employee", example = "1", required = true, position = 0)
    private long id;
    @ApiModelProperty(notes = "First name of the employee", example = "James", required = true, position = 1)
    private String first_name;
    @ApiModelProperty(notes = "Last name of the employee", example = "Bond", required = true, position = 2)
    private String last_name;
    @ApiModelProperty(notes = "Email address of the employee", example = "jamesbond@gmail.com", required = true, position = 3)
    private String email;
    @ApiModelProperty(notes = "Salary of the employee", example = "4000", required = true, position = 4)
    private double salary;

    public Employee() {
    }

    public Employee(long id, String first_name, String last_name, String email, double salary) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(first_name, employee.first_name) &&
                Objects.equals(last_name, employee.last_name) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, salary);
    }
}
