******************* Spring Boot Profiles *******************
Spring Boot Profiles**
---------------------
      Spring Boot Profiles allow you to define the spepecific configurations for Devlopment, QA, PreProduction, Production And Other evnironments.
      By using the Profiles, you can seperate the Configuration concerns for different enviroments and easily switch between them without modifying the code.
	  
Create the Spring Boot Application(starter)	  
	  
   Name         : SpringBootProfilesDemo
   Type         : Maven
   Packaging    : War
   Java Version : 17
   Language     : Java
   Group        : com.sangam
   Artifact     : SpringBootProfilesDemo
   Version      : 1.0
   Description  : Spring Boot Profiles Demo App
   Package      : com.sangam
   Dependencies : Spring Boot DevTools,Lombok,Spring Web

	  
Example:- Create the below properties file
Right click on the Resource package > new > file > <propertiesName>.properties > finish
          application.properties
          application-dev.properties
          application-qa.properties
          application-preprod.properties
          application-prod.properties
		  
		  
1.application.properties
------------------------
#Application Profiles Configuration
spring.profiles.active=${ACTIVE_PROFILE:dev}

#Server Port Configuration
server.port=${SERVER_PORT:9090}

2.application-dev.properties
----------------------------
#Profiles Configuration
ACTIVE_PROFILE=dev

#Server Configuration
SERVER_PORT=8081

3.application-qa.properties
---------------------------
#Profiles Configuration
ACTIVE_PROFILE=qa

#Server Configuration
SERVER_PORT=8082

4.application-preprod.properties
--------------------------------
#Profiles Configuration
ACTIVE_PROFILE=preprod

#Server Configuration
SERVER_PORT=8083

5.application-prod.properties
-----------------------------
# Profiles Configuration
ACTIVE_PROFILE=prod

# Server Configuration
SERVER_PORT=8084		  
		  
What about the below expression
-------------------------------------
spring.profiles.active=${ACTIVE_PROFILE:dev}
		  
		  
${ACTIVE_PROFILE:dev} 
---------------------
This is a placeholder expression used to define the active profiles. It consists of two parts separated by a colon (:).

${ACTIVE_PROFILE} 
------------------
Represents the name of an environment variable. It is enclosed in ${} to indicate that it should be resolved as an expression.

dev 
---
dev is the default value specified after the colon. If the environment variable ACTIVE_PROFILE is not set or is empty, the default value dev will be used as the active profile.

In other words, the application will attempt to read the value of the environment variable ACTIVE_PROFILE. If the variable is not set or has no value, the active profile will be set to dev. However, if the variable is set to a value, that value will be used as the active profile instead.		  

** We can activate multiple profiles at same time
   Example:- spring.profiles.active=dev,qa
   If multiple profiles is used and any key is ambiguity means it is going to last profile key value
   In the Above Example it is going to take the qa key value if any ambiguity with key

** How to give spring profiles configurations
   1>Use key spring.profiles.active=profile
     =>We can provide using 3 ways. Those are
   1. Right click on the project > Run as > Run Configurations > Arguments > Program Arguments >   --spring.profiles.active=prod > apply > Run
   2.  Command Line Arguments > --spring.profiles.active=prod
   3.  In application.properties > spring.profiles.active=prod


********************************* 1st Example ***************************************
   Name         : SpringBootProfilesDemo
   Type         : Maven
   Packaging    : War
   Java Version : 17
   Language     : Java
   Group        : com.sangam
   Artifact     : SpringBootProfilesDemo
   Version      : 1.0
   Description  : Spring Boot Profiles Demo App
   Package      : com.sangam
   Dependencies : Spring Boot DevTools,Lombok,Spring Web
   -------------------------------------------------------
          application.properties
          application-dev.properties
          application-qa.properties
          application-preprod.properties
          application-prod.properties
   -------------------------------------------------------
          1.application.properties
          ------------------------
          #Application Profiles Configuration
          spring.profiles.active=${ACTIVE_PROFILE:dev}

          #Server Port Configuration
          server.port=${SERVER_PORT:9090}

		  2.application-dev.properties
		  ----------------------------
		  #Profiles Configuration
		  ACTIVE_PROFILE=dev

		  #Server Configuration
		  SERVER_PORT=8081

		  3.application-qa.properties
		  ---------------------------
		  #Profiles Configuration
		  ACTIVE_PROFILE=qa

		  #Server Configuration
		  SERVER_PORT=8082

		  4.application-preprod.properties
		  --------------------------------
		  #Profiles Configuration
		  ACTIVE_PROFILE=preprod

		  #Server Configuration
		  SERVER_PORT=8083

		  5.application-prod.properties
		  -----------------------------
		  # Profiles Configuration
		  ACTIVE_PROFILE=prod

		  # Server Configuration
		  SERVER_PORT=8084	
	-------------------------------------------------------
          set the profile 
		  -----------------------------  
		  Right click on the project > Run as > Run Configurations > Arguments > Program Arguments >   --spring.profiles.active=prod > apply > Run
		  
********************************* 2nd Example ***************************************	
   Name         : 01SpringBootProfiles
   Type         : Maven
   Packaging    : War
   Java Version : 17
   Language     : Java
   Group        : com.sangam
   Artifact     : 01SpringBootProfiles
   Version      : 1.0
   Description  : Spring Boot Profiles App
   Package      : com.sangam
   Dependencies : Spring Boot DevTools,Lombok,Spring Web
   -------------------------------------------------------
   com.sangam.profile.runner.MyRunner.java
   com.sangam.profile.service.GenericService.java
   com.sangam.profile.service.impl.DevService.java
   com.sangam.profile.service.impl.QAService.java
   com.sangam.profile.service.impl.PreProdService.java
   com.sangam.profile.service.impl.ProdService.java
   
   -------------------------------------------------------
          application.properties
          application-dev.properties
          application-qa.properties
          application-preprod.properties
          application-prod.properties 
   -------------------------------------------------------		  
		  1.application.properties
          ------------------------
          my.profile.code=Hello From Default

		  2.application-dev.properties
		  ----------------------------
		  my.profile.code=Hello From Dev

		  3.application-qa.properties
		  ---------------------------
		  my.profile.code=Hello From QA

		  4.application-preprod.properties
		  --------------------------------
		  my.profile.code=Hello From Pre Prod

		  5.application-prod.properties
		  -----------------------------
		  my.profile.code=Hello From Prod	
		  
		  com.sangam.profile.runner.MyRunner.java
		  -------------------------------------------------------
		  package com.sangam.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.sangam.service.GenericService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MyRunner implements CommandLineRunner {

	private final GenericService service;

	@Override
	public void run(String... args) throws Exception {
		service.executeTask();
	}

}

		  
		  
		  com.sangam.profile.service.GenericService.java
		  -------------------------------------------------------
		  package com.sangam.service;

public interface GenericService {

	public void executeTask();
}

		  
		  com.sangam.profile.service.impl.DevService.java
		  -------------------------------------------------------
		  package com.sangam.service.impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.sangam.service.GenericService;

@Component
@Profile("default")
public class DefaultServiceImpl implements GenericService{


	@Value("${my.profile.code}")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ProdServiceImpl [code=" + code + "]";
	}

	@Override
	public void executeTask() {
		System.out.println("From Default Service Implementation");
		System.out.println("Code is :: "+code);

	}

}

		  
		  com.sangam.profile.service.impl.QAService.java
		  -------------------------------------------------------
		  package com.sangam.service.impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.sangam.service.GenericService;

@Component
@Profile("qa")
public class QAServiceImpl implements GenericService {

	@Value("${my.profile.code}")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ProdServiceImpl [code=" + code + "]";
	}

	@Override
	public void executeTask() {
		System.out.println("From QA Service Implementation");
		System.out.println("Code is :: " + code);

	}

}

		  
		  com.sangam.profile.service.impl.PreProdService.java
		  -------------------------------------------------------
		  package com.sangam.service.impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.sangam.service.GenericService;

@Component
@Profile("preprod")
public class PreproServiceImpl implements GenericService {

	@Value("${my.profile.code}")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ProdServiceImpl [code=" + code + "]";
	}

	@Override
	public void executeTask() {
		System.out.println("From PreProd Service Implementation");
		System.out.println("Code is :: " + code);

	}

}

		  
		  com.sangam.profile.service.impl.ProdService.java
		  -------------------------------------------------------
		  package com.sangam.service.impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.sangam.service.GenericService;

@Component
@Profile("prod")
public class ProdServiceImpl implements GenericService {

	@Value("${my.profile.code}")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ProdServiceImpl [code=" + code + "]";
	}

	@Override
	public void executeTask() {
		System.out.println("From Prod Service Implementation");
		System.out.println("Code is :: " + code);

	}

}
