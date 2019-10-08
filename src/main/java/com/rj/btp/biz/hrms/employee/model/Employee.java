package com.rj.btp.biz.hrms.employee.model;


import com.rj.btp.biz.hrms.department.model.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_employee")
@Entity
public class Employee implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "department_id")
    private Integer departmentId;

    @OneToOne(cascade = CascadeType.ALL)//employee是地址关系的维护端，当删除employee时会及联删除address
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;//地址

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)//设置在employee表中的关联字段(外键)
    private Department department;


}
