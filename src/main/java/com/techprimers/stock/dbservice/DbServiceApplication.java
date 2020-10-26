package com.techprimers.stock.dbservice;

import com.techprimers.stock.dbservice.resource.DbServiceResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackageClasses= DbServiceResource.class)
public class DbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}

}
