package kz.insoft.usercrudapp;


import kz.insoft.usercrudapp.client.DepartmentApiService;
import kz.insoft.usercrudapp.client.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class UserCrudappApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(UserCrudappApplication.class, args);

		UserApiService userApiService = context.getBean(UserApiService.class);
		userApiService.getByEmail("test1@mail.com");

	}

}
