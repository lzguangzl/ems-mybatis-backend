package com.ems.mapper;

import com.ems.model.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface EmployeesMapper {

    List<Employee> findEmployees();

    @Insert("insert into employees(first_name, last_name, email, salary) values (#{first_name}, #{last_name}, #{email}, #{salary})")
    int addEmployee(Employee employee);

    @Update("update employees set salary=#{salary} where id=#{id}")
    int updateEmployee(@Param("id") Long id, @Param("salary") Double salary) ;

    @Delete("delete from employees where id=#{id}")
    int deleteEmployee(@Param("id") Long id);
}
