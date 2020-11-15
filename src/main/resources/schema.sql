CREATE DATABASE IF NOT EXISTS employee_management_system;

USE employee_management_system;

DROP TABLE IF EXISTS employee_management_system.employees;

CREATE TABLE employee_management_system.employees
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    email      VARCHAR(255),
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    salary     DOUBLE
);

DROP TABLE IF EXISTS employee_management_system.audit_employees;

CREATE TABLE employee_management_system.audit_employees
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    emp_id        INT          NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    changes       VARCHAR(1000),
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)


