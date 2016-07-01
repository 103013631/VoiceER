package com.ge.predix.digitalyst.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author 	
 *
 *Entry class to start Digitalyst application using Spring boot
 */

@Configuration
@ComponentScan(basePackages={"com.ge.predix.digitalyst"})
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages={"com.ge.predix.digitalyst"})
@EntityScan(basePackages={"com.ge.predix.digitalyst"})
public class DigitalystApplication {

	private static Log log = LogFactory.getLog(DigitalystApplication.class);
	
    public static void main(String[] args) {
    	log.info("Starting digitalyst application");
        SpringApplication.run(DigitalystApplication.class, args);
    }
}
