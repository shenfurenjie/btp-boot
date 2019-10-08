package com.rj.btp.biz.hrms.department.model;

import com.rj.btp.biz.hrms.employee.model.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "department")
    //@JoinColumn(name = "department_id")
    private List<Employee> employeeList;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "location_id")
//    private Location location;


}
