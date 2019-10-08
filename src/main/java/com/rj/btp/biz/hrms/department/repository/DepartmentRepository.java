package com.rj.btp.biz.hrms.department.repository;

import com.rj.btp.biz.hrms.department.model.Department;
import com.rj.btp.framework.mapper.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer> {
}
