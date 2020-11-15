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
        Employee createdEmployee = null;
        int isCreated = employeesMapper.addEmployee(employee);
        if(isCreated != 0) {
            createdEmployee = employeesMapper.getEmployeeLastAdded();
        }
        employeesMapper.addEmployeeAudit(createdEmployee.getId(), createdEmployee.getFirst_name(), createdEmployee.getLast_name(), "Employee created");
        return employeesMapper.findEmployees();
    }

    /**
     * Update employee by id
     */
    @PutMapping(path = "/employees/{id}")
    public List<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        double salary = employee.getSalary();
        employeesMapper.updateEmployee(id, salary);
        employeesMapper.addEmployeeAudit(id, employee.getFirst_name(), employee.getLast_name(), "Employee updated");
        return employeesMapper.findEmployees();
    }

    /**
     * Delete employee by id
     */
    @DeleteMapping(path = "/employees/{id}")
    public List<Employee> deleteEmployee(@PathVariable Long id) {
        Employee deletedEmployee = employeesMapper.getEmployeeById(id);
        employeesMapper.deleteEmployee(id);
        employeesMapper.addEmployeeAudit(id, deletedEmployee.getFirst_name(), deletedEmployee.getLast_name(), "Employee deleted");
        return employeesMapper.findEmployees();
    }
}
