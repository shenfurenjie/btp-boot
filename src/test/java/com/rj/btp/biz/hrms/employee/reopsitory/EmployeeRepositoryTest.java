package com.rj.btp.biz.hrms.employee.reopsitory;

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

}