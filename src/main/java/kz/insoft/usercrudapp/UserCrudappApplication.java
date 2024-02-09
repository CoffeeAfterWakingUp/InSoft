package kz.insoft.usercrudapp;

import kz.insoft.usercrudapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class UserCrudappApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(UserCrudappApplication.class, args);

		UserService userService = context.getBean("userServiceImpl", UserService.class);

		LocalDate now = LocalDate.now(ZoneId.of("UTC+6"));

		log.info("By Email: {}", userService.findByEmail("test1@mail.com"));
		log.info("By First name and Last Name: {}", userService.findByFirstNameAndLastName("test1", "test1"));
		log.info("Users Count: {}", userService.findUsersCount());
		log.info("By address: {}", userService.findByAddressIn(Arrays.asList("test1", "test")));
		log.info("By address: {}", userService.findByAddressIn(Arrays.asList("test", "test2")));
		log.info("Birth Date After {}: {}", now, userService.findByBirthDateAfter(now));
		log.info("Birth Date Before {}: {}", now, userService.findByBirthDateBefore(now));

		userService.deleteByEmail("test2@mail.com");
	}

}
