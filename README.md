# Employee Management System - Mybatis

An Employee Management System backend using Spring Boot

## Purpose of the project

To show understanding for MyBatis and Log4j2

## Project setup

This project is created using [Spring Initializer](https://start.spring.io/)

Spring Boot

- 2.3.5

Build Tool

- Maven

Language

- Java, 13

Dependencies

- Spring Boot DevTools
- Spring Web
- MyBatis
- MySQL Driver
- Log4j2
- JUnit5

### Create the database and tables using SQL
````
spring.datasource.schema= # Schema (DDL) script resource references.
spring.datasource.data= # Data (DML) script resource references.
Example: spring.datasource.schema = classpath:/abc.sql,classpath:/abc2.sql
````
- No need to change the SQL filenames
- Can Keep schema generation and insertion in the same file
- Can specify multiple files
- Spring boot already configures Hibernate to create your schema based on your entities. To create it using SQL (in src/main/resources) files set <b>below</b> in application.properties.
````
spring.jpa.hibernate.ddl-auto=none
````
Create schema.sql (to create the tables) and data.sql (to insert the records) in src/main/resources
- schema.sql
````
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
````
- data.sql
````
insert into employees(email, first_name, last_name,salary) values('tony_stark@gmail.com', 'Tony', 'Stark', 20000);
insert into employees(email, first_name, last_name,salary) values('chris_pang@gmail.com', 'Chris', 'Pang', 4000);
````

- useGeneratedKeys
````
(insert and update only) 
This tells MyBatis to use the JDBC getGeneratedKeys method to retrieve keys generated internally by the database (e.g. auto increment fields in RDBMS like MySQL or SQL Server).
Default: false.

<insert id="addEmployeeAudit" parameterType="EmployeeAudit" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
````

- keyProperty
````
(insert and update only) 
Identifies a property into which MyBatis will set the key value returned by getGeneratedKeys, or by a selectKey child element of the insert statement. 
Default: unset. 
Can be a comma separated list of property names if multiple generated columns are expected.

<insert id="addEmployeeAudit" parameterType="EmployeeAudit" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
````

- keyColumn
````
(insert and update only) 
Sets the name of the column in the table with a generated key. 
This is only required in certain databases (like PostgreSQL) when the key column is not the first column in the table. 
Can be a comma separated list of columns names if multiple generated columns are expected.

<insert id="addEmployeeAudit" parameterType="EmployeeAudit" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
````