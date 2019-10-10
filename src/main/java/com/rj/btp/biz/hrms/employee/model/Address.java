package com.rj.btp.biz.hrms.employee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_address")
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "snowflakeIdGenerator")
    @GenericGenerator(name = "snowflakeIdGenerator", strategy = "com.rj.btp.framework.id.SnowflakeIdGenerator")
    private Long id;

    @Column(name = "address")
    private String address;

    //如果不需要根据Address级联查询Employee，可以注释掉
//    @OneToOne(mappedBy = "address", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
//    private Employee employee;


}
