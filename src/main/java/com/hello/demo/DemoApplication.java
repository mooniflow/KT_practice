package com.hello.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springdoc.core.annotations.OpenAPIDefinition;
import org.springdoc.core.annotations.Info

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "My API",
        version = "v1",
        description = "My API documentation"
    )
)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
