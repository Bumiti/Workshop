package com.workshop;

import com.workshop.dto.UserRegisterRequest;
import com.workshop.model.userModel.Roles;
import com.workshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkshopApplicationTests {
	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {
	
	}

}
