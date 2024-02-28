package kz.insoft.usercrudapp;


import kz.insoft.usercrudapp.client.DepartmentApiService;
import kz.insoft.usercrudapp.client.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class UserCrudappApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(UserCrudappApplication.class, args);

		UserApiService userApiService = context.getBean(UserApiService.class);
		DepartmentApiService departmentApiService = context.getBean(DepartmentApiService.class);

//		userApiService.getAllUsers();
//		userApiService.getById(23L);
//		userApiService.getByEmail("newEmail23@mail.com");
		userApiService.deleteUserById();


		//departmentApiService.createDepartment();
		//departmentApiService.updateDepartment();

	}

}
