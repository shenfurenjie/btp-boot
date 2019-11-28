package com.tiger.btp.app.service;

import com.tiger.btp.school.school_model.mapper.SchoolBaseMapper;
import com.tiger.btp.school.school_model.model.SchoolBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: TigerRen
 * @Date: 2019/11/25 3:52 PM
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataModelDaoTest {

    @Autowired
    SchoolBaseMapper schoolBaseMapper;

    @Test
    public void testSchoolMapper() {
        SchoolBase school = schoolBaseMapper.selectById(1);
        log.info("school is {}", school);
    }
}
