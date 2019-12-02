package com.tiger.btp.app;

import com.tiger.btp.BtpApplication;
import com.tiger.btp.school.school_model.model.SchoolBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: TigerRen
 * @Date: 2019/11/29 9:45 AM
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BtpApplication.class)
public class DataModelDaoFactoryTest {

    @Autowired
    DataModelDaoFactory dataModelDaoFactory;
    @Autowired
    DataModelFactory dataModelFactory;

    @Test
    public void testSelectById() {
        SchoolBase schoolBase = (SchoolBase) dataModelDaoFactory.getDataModelDAO(SchoolBase.MODEL_ID).selectById(1);
        log.info("schoolBase is {}", schoolBase);
    }

    @Test
    public void testInsert() {
        SchoolBase schoolBase = new SchoolBase();
        schoolBase.setSchoolName("school002");
        int flag = dataModelDaoFactory.getDataModelDAO(SchoolBase.MODEL_ID).insert(schoolBase);
        log.info("insert is {}", flag);
    }
}