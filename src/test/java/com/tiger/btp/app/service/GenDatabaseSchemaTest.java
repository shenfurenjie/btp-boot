package com.tiger.btp.app.service;

import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.app.DataModelFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: TigerRen
 * @Date: 2019/11/28 9:43 AM
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenDatabaseSchemaTest {

    @Autowired
    GenDatabaseSchema genDatabaseSchema;

    @Autowired
    DataModelFactory dataModelFactory;

    @Test
    public void ddl() {
        Map dataModelMap = dataModelFactory.getDataModelExtMap();
        List<DataModelExt> mapValueList = new ArrayList<DataModelExt>(dataModelMap.values());
        genDatabaseSchema.ddl(mapValueList, false);
    }
}