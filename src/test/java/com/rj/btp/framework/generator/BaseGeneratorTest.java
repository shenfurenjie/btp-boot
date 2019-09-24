package com.rj.btp.framework.generator;

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
    public void executeGenerate() {
        baseGenerator.executeGenerate("t_user", "com.rj.btp.hrms", "userManager");
    }
}