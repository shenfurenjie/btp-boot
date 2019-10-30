package com.tiger.btp.framework.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseGeneratorTest {

    @Autowired
    BaseGenerator baseGenerator;

    @Test
    public void executeGenerateUser() {
        baseGenerator.executeGenerate("t_user", "com.rj.btp.biz.hrms", "userManager");
    }

    @Test
    public void executeGenerateDep() {
        baseGenerator.executeGenerate("t_department", "com.rj.btp.biz.hrms", "departmentManager");
    }
}