package com.ems.mapper;

import com.ems.model.Employee;
import com.ems.model.EmployeeAudit;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeesMapperIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeesMapperIntegrationTest.class);

    @Autowired
    EmployeesMapper employeesMapper;

    @BeforeAll
    static void setup(TestInfo testInfo) {
        logger.info("@BeforeAll - Start executing {}", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        logger.info("@BeforeEach - executes before {}", testInfo.getDisplayName());
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        logger.info("@AfterEach - executed after {}", testInfo.getDisplayName());
    }

    @AfterAll
    static void done(TestInfo testInfo) {
        logger.info("@AfterAll - End executing {}", testInfo.getDisplayName());
    }

    @Order(2)
    @DisplayName("Test EmployeesMapper: getEmployees()")
    @Test
    void testGetEmployees() {
        assertEquals(9, employeesMapper.getEmployees().size());
    }

    @Order(2)
    @DisplayName("Test EmployeesMapper: getEmployeeById(id)")
    @Test
    void testGetEmployeeById() {
        Employee employeeExpected = new Employee(1, "Tony", "Stark", "tony_stark@gmail.com", 20000.0);
        assertEquals(employeeExpected, employeesMapper.getEmployeeById((long) 1));
    }

    @Order(3)
    @Disabled("Test EmployeesMapper: getEmployeeLastAdded() *NOT IN USE*")
    @DisplayName("Test EmployeesMapper: getEmployeeLastAdded()")
    @Test
    void testGetEmployeeLastAdded() {
        Employee employeeToAdd = new Employee(4, "'Henry'", "'Golding'", "henry_golding@gmail.com'", 8000.0);
        int id = employeesMapper.addEmployee(employeeToAdd);
        if (id != 0) {
            assertEquals(employeeToAdd, employeesMapper.getEmployeeLastAdded());
        }
    }

    @Order(4)
    @DisplayName("Test EmployeesMapper: updateEmployee()")
    @Test
    void testUpdateEmployee() {
        assertEquals(1, employeesMapper.updateEmployee((long) 3, 7000.0));
    }

    @Order(5)
    @DisplayName("Test EmployeesMapper: deleteEmployee()")
    @Test
    void testDeleteEmployee() {
        assertEquals(1, employeesMapper.deleteEmployee((long) 1));
    }

    @Order(6)
    @DisplayName("Test EmployeesMapper: addEmployeeAudit()")
    @Test
    void testAddEmployeeAudit() {
        EmployeeAudit employeeAudit1 = new EmployeeAudit(1, "Tony", "Stark", "Employee updated");
        assertEquals(1, employeesMapper.addEmployeeAudit(employeeAudit1));
    }


}
