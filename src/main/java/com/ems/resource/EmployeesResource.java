package com.ems.resource;

import com.ems.mapper.EmployeesMapper;
import com.ems.model.Employee;
import com.ems.model.EmployeeAudit;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api()
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
    @ApiOperation(value = "Find all employees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation")
    })
    @GetMapping(path = "/employees")
    public List<Employee> getAllEmployees() {
        logger.info("Get All Employees");
        return employeesMapper.getEmployees();
    }

    /**
     * Create employee
     */
    @ApiOperation(value = "Create employee")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee created"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Employee already exists")
    })
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
    @ApiOperation(value = "Update employee by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
            @ApiResponse(code = 404, message = "Employee not found")
    })
    @PutMapping(path = "/employees/{id}")
    public List<Employee> updateEmployee(@ApiParam("Id of employee to be updated. Cannot be empty.") @PathVariable Long id, @ApiParam("Employee to update. Cannot be null or empty.") @RequestBody Employee employee) {
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
    @ApiOperation(value = "Delete employee by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 404, message = "Employee not found")
    })
    @DeleteMapping(path = "/employees/{id}")
    public List<Employee> deleteEmployee(@ApiParam("Id of employee to be deleted. Cannot be empty.") @PathVariable Long id) {
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
