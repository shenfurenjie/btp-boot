package com.rj.btp;

import com.rj.btp.framework.CustomJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackageClasses = {BtpApplication.class, Jsr310JpaConverters.class})
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomJpaRepositoryFactoryBean.class)
@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
public class BtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtpApplication.class, args);
    }

}
