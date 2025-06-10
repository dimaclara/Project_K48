package teck.marie.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication //(exclude = { SecurityAutoConfiguration.class })
public class UsermanageApplication  {

	public static void main(String[] args) {
		SpringApplication.run(UsermanageApplication.class, args);
	}

}
