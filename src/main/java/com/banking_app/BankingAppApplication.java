package com.banking_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info= @Info(
				title = "BANKING APP For Money Transfer",
				description = "Simple Banking app REST API's Where we are adding money,withdraw and transferring the money",
				contact = @Contact(
						name = "Ram Tiwari",
						email = "ramtiwari8716@gmail.com",
						url = "github.com/ramnageena"
				)
		)
)
@SpringBootApplication
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

}
