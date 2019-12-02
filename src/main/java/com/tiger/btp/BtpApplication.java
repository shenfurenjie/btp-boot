package com.tiger.btp;

import com.tiger.btp.framework.CustomJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan
@EntityScan(basePackageClasses = {BtpApplication.class, Jsr310JpaConverters.class})
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomJpaRepositoryFactoryBean.class)
@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
public class BtpApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BtpApplication.class, args);
        //AppContextUtil.setApplicationContext(context);
    }

}
