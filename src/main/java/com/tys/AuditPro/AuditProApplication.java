package com.tys.AuditPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true)
public class AuditProApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditProApplication.class, args);
	}

}
