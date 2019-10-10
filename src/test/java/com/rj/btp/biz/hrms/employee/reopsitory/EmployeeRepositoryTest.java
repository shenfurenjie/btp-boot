package com.rj.btp.biz.hrms.employee.reopsitory;

import com.rj.btp.biz.hrms.employee.model.Address;
import com.rj.btp.biz.hrms.employee.model.Employee;
import com.rj.btp.framework.model.condition.QueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void testEmployee() {
        List<Employee> employeeList = employeeRepository.findByCondition(new QueryCondition());
        log.info("employeeList is {}", employeeList);
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee();
        employee.setEmail("zhangdan@qq.com");
        employee.setFirstName("zhang");
        employee.setLastName("dan");
        employee.setDepartmentId(1);
        Address address = new Address();
        address.setAddress("浦口");
        employee.setAddress(address);
        Employee result = employeeRepository.saveModel(employee);
        log.info("employeeList is {}", employee);
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setId(631818937964691456l);
        employee.setEmail("zhangdan@qq.com");
        employee.setFirstName("zhang");
        employee.setLastName("dan");
        employee.setDepartmentId(1);
        Address address = new Address();
        address.setId(631818938019217408l);
        address.setAddress("江北新区");
        employee.setAddress(address);
        Employee result = employeeRepository.saveModelExcludeNulls(employee);
        log.info("employeeList is {}", employee);
    }

}