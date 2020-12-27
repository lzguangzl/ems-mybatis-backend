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
- Swagger

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

### Swagger
- Springfox implementation of the Swagger specification
````
The configuration of Swagger mainly centers around the Docket bean.
After defining the Docket bean, its select() method returns an instance of ApiSelectorBuilder, which provides a way to control the endpoints exposed by Swagger.
We can configure predicates for selecting RequestHandlers with the help of RequestHandlerSelectors and PathSelectors. 
Using any() for both will make documentation for our entire API available through Swagger.

To verify if Swagger is working:
http://localhost:8080/<your-app-root>/v2/api-docs
http://localhost:8080/<your-app-root>/swagger-ui.html
````

Name | Description 
--- | --- 
@Api | Marks a class as a Swagger resource.
@ApiModel |	Provides additional information about Swagger models.
@ApiModelProperty |	Adds and manipulates data of a model property.
@ApiOperation |	Describes an operation or typically a HTTP method against a specific path.
@ApiParam | Adds additional meta-data for operation parameters.
@ApiResponse | Describes a possible response of an operation.
@ApiResponses | A wrapper to allow a list of multiple ApiResponse objects.

- You may encounter this warning when accessing your swagger-ui.html (swagger documentation)
````
07-12-2020 01:02:44.076 [http-nio-8080-exec-10] WARN  io.swagger.models.parameters.AbstractSerializableParameter - Illegal DefaultValue null for parameter type integer
java.lang.NumberFormatException: For input string: ""
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:68) ~[?:?]
	at java.lang.Long.parseLong(Long.java:709) ~[?:?]
	at java.lang.Long.valueOf(Long.java:1151) ~[?:?]
	at io.swagger.models.parameters.AbstractSerializableParameter.getExample(AbstractSerializableParameter.java:412) [swagger-models-1.5.20.jar:1.5.20]
````
- As workaround, you can ignore AbstractSerializableParameter class severity warning, by set it severity to error
- These configuration will silent the warning messages. To leave the severity as is, and make the warning disappear, add the below code into application properties:
````
logging.level.io.swagger.models.parameters.AbstractSerializableParameter=ERROR
````

### JUnit 5
- @TestMethodOrder to control the execution order of tests.
- @TestMethodOrder(MethodOrderer.OrderAnnotation.class) + @Order(x) annotation to enforce tests to run in a specific order, where x is the order number.
- @TestMethodOrder(Alphanumeric.class) to run tests based on their names in alphanumeric order.
- Custom order by implementing the MethodOrderer Interface
````
Example of custom order:

public class CustomOrder implements MethodOrderer {
    @Override
    public void orderMethods(MethodOrdererContext context) {
        context.getMethodDescriptors().sort(
         (MethodDescriptor m1, MethodDescriptor m2)->
           m1.getMethod().getName().compareToIgnoreCase(m2.getMethod().getName()));
    }
}

@TestMethodOrder(CustomOrder.class)
public class CustomOrderUnitTest {
    ... ... 
}
````