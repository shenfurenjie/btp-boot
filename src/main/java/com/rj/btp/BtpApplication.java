package com.rj.btp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EntityScan(basePackageClasses = { DemoApplication.class, Jsr310JpaConverters.class })
@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
public class BtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtpApplication.class, args);
    }

}
