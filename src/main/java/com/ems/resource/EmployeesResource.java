package com.ems.resource;

import com.ems.mapper.EmployeesMapper;
import com.ems.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class EmployeesResource {
    private static final Logger logger = LoggerFactory.getLogger(EmployeesResource.class);

    private final EmployeesMapper employeesMapper;

    public EmployeesResource(EmployeesMapper employeesMapper) {
        this.employeesMapper = employeesMapper;
    }

    /**
     * Get all employees
     */
    @GetMapping(path = "/employees")
    public List<Employee> getAllEmployees() {
        logger.info("Get All Employees");
        return employeesMapper.findEmployees();
    }

    /**
     * Create employee
     */
    @PostMapping(path = "/employees")
    public List<Employee> createEmployee(@RequestBody Employee employee) {
        employeesMapper.addEmployee(employee);
        return employeesMapper.findEmployees();
    }

    /**
     * Update employee by id
     */
    @PutMapping(path = "/employees/{id}")
    public List<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Double salary = employee.getSalary();
        employeesMapper.updateEmployee(id, salary);
        return employeesMapper.findEmployees();
    }

    /**
     * Delete employee by id
     */
    @DeleteMapping(path = "/employees/{id}")
    public  List<Employee> deleteEmployee(@PathVariable Long id) {
        employeesMapper.deleteEmployee(id);
        return employeesMapper.findEmployees();
    }
}
