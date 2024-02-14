package kz.insoft.usercrudapp;

import kz.insoft.usercrudapp.entity.Department;
import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.entity.UserDetails;
import kz.insoft.usercrudapp.entity.UserPhone;
import kz.insoft.usercrudapp.repository.DepartmentRepository;
import kz.insoft.usercrudapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class UserCrudappApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(UserCrudappApplication.class, args);

		UserService userService = context.getBean("userServiceImpl", UserService.class);

		DepartmentRepository departmentRepository = context.getBean(DepartmentRepository.class);


		// ONE TO ONE

//		User user = userService.findById(1L);
//		UserDetails userDetails = user.getUserDetails();
//		userDetails.setActive(true);
//		userDetails.setUpdatedTime(LocalDateTime.now());
//
//		User updUser = userService.updateAndReturn(user, user.getId());
//
//		log.info("Updated user: {}", updUser);

//		userService.delete(1L);


		// ONE TO MANY

//
//		User user = userService.findById(2L);
//
//		UserPhone phone1 = UserPhone.builder()
//				.phoneNumber("87077654334")
//				.type("WORK")
//				.build();
//
//		UserPhone phone2 = UserPhone.builder()
//				.phoneNumber("87077659665")
//				.type("HOME")
//				.build();
//
//		user.addPhone(phone1);
//		user.addPhone(phone2);
//
//		User updUser = userService.updateAndReturn(user, user.getId());
//
//		log.info("Updated User: {}", updUser);

		// MANY TO MANY

		User user = userService.findById(2L);

		User user3 = userService.findById(3L);

		Department department1 = Department.builder()
				.name("IT")
				.build();

		Department department2 = Department.builder()
				.name("Finance")
				.build();

		department1.addUser(user);
		department1.addUser(user3);

		department2.addUser(user);

		Department itDept = departmentRepository.save(department1);
		Department financeDept = departmentRepository.save(department2);

		log.info("Department IT: {}", itDept);
		log.info("Department Finance: {}", financeDept);

		/**
		 *
		 *  Update
		 *  Delete
		 *
		 * */





	}

}
