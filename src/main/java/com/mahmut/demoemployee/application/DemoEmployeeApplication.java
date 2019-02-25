package com.mahmut.demoemployee.application;

import com.mahmut.demoemployee.application.controller.UserHomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.mahmut.demoemployee.application.repositories"})
@ComponentScan(basePackages = {"com.mahmut.demoemployee.application.dao"}, basePackageClasses = UserHomeController.class)
@EntityScan(basePackages = {"com.mahmut.demoemployee.application.entity"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class DemoEmployeeApplication 
{ 
	public static void main(String[] args)
	{
		SpringApplication.run(DemoEmployeeApplication.class, args);
	}
}
