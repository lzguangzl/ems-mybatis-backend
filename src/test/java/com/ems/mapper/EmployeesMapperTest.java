package com.ems.mapper;

import com.ems.model.Employee;
import com.ems.model.EmployeeAudit;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeesMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeesMapperTest.class);

    @Mock
    EmployeesMapper employeesMapper;

    @BeforeAll
    static void setup() {
        logger.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        logger.info("@BeforeEach - executes before each test method in this class");

        Employee employee1 = new Employee(1, "Joe", "Doe", "joe_doe@gmail.com", 1200.0);
        Employee employee2 = new Employee(2, "Jane", "Doe", "jane_doe@gmail.com", 3000.0);
        Employee employee3 = new Employee(3, "Kate", "Pang", "kate_pang@hotmail.com", 4000.0);
        Employee employee4 = new Employee(4, "Jay", "Ho", "jay_ho@hotmail.com", 6000.0);
        EmployeeAudit employeeAudit1 = new EmployeeAudit(1, "Joe", "Doe", "Employee updated");

        when(employeesMapper.getEmployees()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        when(employeesMapper.getEmployeeById((long) 1)).thenReturn(employee1);
        when(employeesMapper.getEmployeeLastAdded()).thenReturn(employee4);
        when(employeesMapper.addEmployee(employee4)).thenReturn(4);
        when(employeesMapper.updateEmployee((long) 3, 7000.0)).thenReturn(1);
        when(employeesMapper.deleteEmployee((long) 1)).thenReturn(1);
        when(employeesMapper.addEmployeeAudit(employeeAudit1)).thenReturn(1);
    }

    @AfterEach
    void tearDown() {
        logger.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        logger.info("@AfterAll - executed after all test methods.");
    }

    @DisplayName("Test EmployeesMapper: getEmployees()")
    @Test
    void testGetEmployees() {
        assertEquals(3, employeesMapper.getEmployees().size());
    }

    @DisplayName("Test EmployeesMapper: getEmployeeById(id)")
    @Test
    void testGetEmployeeById() {
        Employee employeeExpected = new Employee(1, "Joe", "Doe", "joe_doe@gmail.com", 1200.0);
        assertEquals(employeeExpected, employeesMapper.getEmployeeById((long) 1));
    }

    @Disabled("Test EmployeesMapper: getEmployeeLastAdded() *NOT IN USE*")
    @DisplayName("Test EmployeesMapper: getEmployeeLastAdded()")
    @Test
    void testGetEmployeeLastAdded() {
        Employee employeeToAdd = new Employee(4, "Jay", "Ho", "jay_ho@hotmail.com", 6000.0);
        int id = employeesMapper.addEmployee(employeeToAdd);
        if (id != 0) {
            assertEquals(employeeToAdd, employeesMapper.getEmployeeLastAdded());
        }
    }

    @DisplayName("Test EmployeesMapper: addEmployee()")
    @Test
    void testAddEmployee() {
        Employee employeeToAdd = new Employee(4, "Jay", "Ho", "jay_ho@hotmail.com", 6000.0);
        assertEquals(4, employeesMapper.addEmployee(employeeToAdd));
    }

    @DisplayName("Test EmployeesMapper: updateEmployee()")
    @Test
    void testUpdateEmployee() {
        assertEquals(1, employeesMapper.updateEmployee((long) 3, 7000.0));
    }

    @DisplayName("Test EmployeesMapper: deleteEmployee()")
    @Test
    void testDeleteEmployee() {
       assertEquals(1, employeesMapper.deleteEmployee((long) 1));
    }

    @DisplayName("Test EmployeesMapper: addEmployeeAudit()")
    @Test
    void testAddEmployeeAudit() {
        EmployeeAudit employeeAudit1 = new EmployeeAudit(1, "Joe", "Doe", "Employee updated");
        assertEquals(1, employeesMapper.addEmployeeAudit(employeeAudit1));
    }
}
