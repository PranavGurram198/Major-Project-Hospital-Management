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
import com.example.springboot.model.Receptionist;
import com.example.springboot.repository.AdminRepository;
import com.example.springboot.repository.ReceptionistRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReceptionistTestCases {
	
	@Autowired
	private ReceptionistRepository receptionistRepository;
	
	//test case for saving 
	@Test
	@Order(1)
	//@Disabled
	public void saveReceptionist() {
		
		Receptionist receptionist = Receptionist.builder()
			.address("palani").receptionistEmailId("dhaarani@gmail.com")
			.receptionistPassword("Arav@3299").age(25).contactNumber("9976934899")
			.firstName("Surya").gender("female").lastName("Pakash")
			.build();
		
		receptionistRepository.save(receptionist);
		
		Assertions.assertThat(receptionist.getReceptionistId()).isGreaterThan(0);
}
	
	
	@Test
	//@Disabled
	@Order(2)
	public void loginReceptionist() {
		
		
		Optional<Receptionist> receptionist = receptionistRepository.findByReceptionistEmailIdAndReceptionistPassword("pranav@gmail.com", "Pranav@3299");
	
		Receptionist newReceptionist=null;
	
		if(receptionist.isPresent()) {
		newReceptionist = receptionist.get();
		}
	//if that newadmin is not null then it will runn otherwise it will fail
		Assertions.assertThat(newReceptionist).isNotNull();
	
}
	
	@Test
	@Order(3)
	//@Disabled
	public void updateReceptionist() {
										//optional
		Receptionist receptionist = receptionistRepository.findById(4000L).get();
	
		receptionist.setFirstName("Aravindh");
	
		Receptionist updatedReceptionist = receptionistRepository.save(receptionist);
	
		Assertions.assertThat(updatedReceptionist.firstName).isEqualTo("Aravindh");
	
	
		
	}
	
	@Test
@Order(4)
	//@Disabled
	public void getAllReceptionists() {

		List<Receptionist> receptionists = receptionistRepository.findAll();
		//if the list size grator than zero then it will run 
	Assertions.assertThat(receptionists.size()).isGreaterThan(0);
}
	@Test
	@Order(5)
	//@Disabled
	public void getReceptionistById() {
		
		Receptionist receptionist = receptionistRepository.findById(4000L).get();
		
	Assertions.assertThat(receptionist.getReceptionistId()).isEqualTo(2L);
	}

	@Test
	@Order(6)
	public void deleteReceptionist() {
		
		Receptionist receptionist = receptionistRepository.findById(6L).get();
		
		receptionistRepository.delete(receptionist);
		//here i have deleted from the database
		
		Receptionist checking = null;
		
		Optional<Receptionist> afterDeleting = receptionistRepository.findById(6L);
		
		if(afterDeleting.isPresent()) {
			checking=afterDeleting.get();
		}
		
		Assertions.assertThat(checking).isNull();
	}

}
