package com.example.springboot;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springboot.model.Admin;
import com.example.springboot.repository.AdminRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminTestCases {
	
	@Autowired
	private AdminRepository adminRepository;
	
	//test case for saving 
	@Test
	@Order(1)
	@Disabled
	public void saveAdmin() {
		
		Admin admin = Admin.builder()
			.address("palani").adminEmailId("dhaarani@gmail.com")
			.adminPassword("Arav@3299").age(25).contactNumber("9976934899")
			.firstName("Surya").gender("female").lastName("Pakash")
			.build();
		
		adminRepository.save(admin);
		
		Assertions.assertThat(admin.getAdminId()).isGreaterThan(0);
}
	
	
	@Test
	@Disabled
	@Order(1)
	public void loginAdmin() {
		
		
		Optional<Admin> admin = adminRepository.findByAdminEmailIdAndAdminPassword("pranav@gmail.com", "Pranav@3299");
	
		Admin newAdmin=null;
	
		if(admin.isPresent()) {
		newAdmin = admin.get();
		}
	//if that newadmin is not null then it will runn otherwise it will fail
		Assertions.assertThat(newAdmin).isNotNull();
	
}
	
	@Test
	@Order(2)
	@Disabled
	public void updateAdmin() {
										//optional
		Admin admin = adminRepository.findById(2L).get();
	
	admin.setFirstName("Aravindh");
	
	Admin updatedAdmin = adminRepository.save(admin);
	
		Assertions.assertThat(updatedAdmin.firstName).isEqualTo("Aravindh");
	
	
		
	}
	
	@Test
@Order(3)
	@Disabled
	public void getAllAdmins() {

		List<Admin> admins = adminRepository.findAll();
		//if the list size grator than zero then it will run 
	Assertions.assertThat(admins.size()).isGreaterThan(0);
}
	@Test
	@Order(4)
	@Disabled
	public void getAdminById() {
		
		Admin admin = adminRepository.findById(2L).get();
		
	Assertions.assertThat(admin.getAdminId()).isEqualTo(2L);
	}

	@Test
	@Order(5)
	public void deleteAdmin() {
		
		Admin admin = adminRepository.findById(6L).get();
		
		adminRepository.delete(admin);
		//here i have deleted from the database
		
	Admin checking = null;
		
		Optional<Admin> afterDeleting = adminRepository.findById(6L);
		
		if(afterDeleting.isPresent()) {
			checking=afterDeleting.get();
		}
		
		Assertions.assertThat(checking).isNull();
	}
}
