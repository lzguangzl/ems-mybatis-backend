package com.ems.resource;

import com.ems.mapper.EmployeesMapper;
import com.ems.model.Employee;
import com.ems.model.EmployeeAudit;
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
        return employeesMapper.getEmployees();
    }

    /**
     * Create employee
     */
    @PostMapping(path = "/employees")
    public List<Employee> createEmployee(@RequestBody Employee employee) {

        EmployeeAudit employeeAudit = null;
        int isCreated = employeesMapper.addEmployee(employee);
        if (isCreated != 0) {
            employeeAudit = new EmployeeAudit();
            employeeAudit.setEmp_id(employee.getId());
            employeeAudit.setFirst_name(employee.getFirst_name());
            employeeAudit.setLast_name(employee.getLast_name());
            employeeAudit.setChanges("Employee created");
        }
        employeesMapper.addEmployeeAudit(employeeAudit);
        return employeesMapper.getEmployees();
    }

    /**
     * Update employee by id
     */
    @PutMapping(path = "/employees/{id}")
    public List<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        double salary = employee.getSalary();
        EmployeeAudit employeeAudit = null;
        int isUpdated = employeesMapper.updateEmployee(id, salary);
        if (isUpdated != 0) {
            employeeAudit = new EmployeeAudit();
            employeeAudit.setEmp_id(id);
            employeeAudit.setFirst_name(employee.getFirst_name());
            employeeAudit.setLast_name(employee.getLast_name());
            employeeAudit.setChanges("Employee updated");
        }
        employeesMapper.addEmployeeAudit(employeeAudit);
        return employeesMapper.getEmployees();
    }

    /**
     * Delete employee by id
     */
    @DeleteMapping(path = "/employees/{id}")
    public List<Employee> deleteEmployee(@PathVariable Long id) {
        EmployeeAudit employeeAudit = null;
        Employee deletedEmployee = employeesMapper.getEmployeeById(id);
        int isDeleted = employeesMapper.deleteEmployee(id);
        if (isDeleted != 0) {
            employeeAudit = new EmployeeAudit();
            employeeAudit.setEmp_id(id);
            employeeAudit.setFirst_name(deletedEmployee.getFirst_name());
            employeeAudit.setLast_name(deletedEmployee.getLast_name());
            employeeAudit.setChanges("Employee deleted");
        }
        employeesMapper.addEmployeeAudit(employeeAudit);
        return employeesMapper.getEmployees();
    }
}
