package com.zigato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Zigato All API's", version = "1.1"), security = {
		@SecurityRequirement(name = "bearerToken") }, servers = {
				@Server(url = "/", description = "Default Server URL") })
		@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")

@SpringBootApplication
@EnableAspectJAutoProxy
public class ZigatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZigatoApplication.class, args);
	}

}
