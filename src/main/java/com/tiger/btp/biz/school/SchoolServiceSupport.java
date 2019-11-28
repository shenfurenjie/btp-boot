package com.tiger.btp.biz.school;

import com.tiger.btp.app.AppServiceSupport;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceSupport extends AppServiceSupport {

    public SchoolServiceSupport() {
        this.setAppXmlPath("apps/school/app.xml");
        this.setDataModelXmlPath(new String[]{"apps/school/dataModel.xml"});
    }
}
