package com.rj.btp.biz.hrms.employee.reopsitory;

import com.rj.btp.biz.hrms.employee.model.Employee;
import com.rj.btp.framework.mapper.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Integer> {
}
