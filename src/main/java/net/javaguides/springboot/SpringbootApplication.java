package net.javaguides.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Spring Boot Web Services",
				description = "Spring Boot Rest API Documentation", version = "v1.0",
				contact = @Contact(name = "Rick", email = "rickyang2910@gmail.com",
						url = "https://github.com/RickYCYang?tab=repositories"),
				license = @License(name = "Apache 2.0", url = "www.google.com")),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation", url = "www.google.com"))
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
