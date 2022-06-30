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
import com.example.springboot.model.Doctor;
import com.example.springboot.repository.DoctorRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorTestCases {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
//	@Test
//	//@Order(1)
//	@Disabled
//	public void saveDoctor() {
//
//		Doctor doctor = Doctor.builder()
//				.address("pune").age(57).contactNumber("1234543211").doctorEmailId("arav@gmail.com")
//				.doctorPassword("Arav@3299").firstName("Aravindh").gender("male").lastName("rajakom")
//				.specialization("ortho").build();
//		
//		doctorRepository.save(doctor);
//		
//		Assertions.assertThat(doctor.getDoctorId()).isGreaterThan(0);
//		
//	}
	
	@Test
	@Order(1)
	public void loginDoctor() {
		
		Optional<Doctor> doctor = doctorRepository.findByDoctorEmailIdAndDoctorPassword("suryaa@gmail.com","Surya@3299");
		
		System.out.println("***************************"+doctor);
		Doctor newDoctor = null;
		System.out.println("***********3333333***************");
		if(doctor.isPresent()) {
			System.out.println("999999999999999999999999999");
			newDoctor=doctor.get();
			System.out.println(newDoctor);
			
		}
		System.out.println("11111111111111111111");
		Assertions.assertThat(newDoctor).isNotNull();
	}
	@Test
	@Order(2)
	@Disabled
	public void updateDoctor() {
										//optional
		Doctor doctor = doctorRepository.findById(2000L).get();
	
		doctor.setFirstName("Aravindh");
	
		Doctor updateDoctor = doctorRepository.save(doctor);
	
		Assertions.assertThat( updateDoctor.firstName).isEqualTo("Aravindh");
	
	
		
	}
	
	@Test
@Order(3)
	//@Disabled
	public void getAllDoctors() {

		List<Doctor> doctors = doctorRepository.findAll();
		//if the list size grator than zero then it will run 
	Assertions.assertThat(doctors.size()).isGreaterThan(0);
}
	@Test
	@Order(4)
	//@Disabled
	public void getDoctorById() {
		
		Doctor doctor = doctorRepository.findById(2000L).get();
		
	Assertions.assertThat(doctor.getDoctorId()).isEqualTo(2000L);
	}

	@Test
	@Order(5)
	public void deleteDoctor() {
		
		Doctor doctor = doctorRepository.findById(2001L).get();
		
		doctorRepository.delete(doctor);
		//here i have deleted from the database
		
		Doctor checking = null;
		
		Optional<Doctor> afterDeleting = doctorRepository.findById(6L);
		
		if(afterDeleting.isPresent()) {
			checking=afterDeleting.get();
		}
		
		Assertions.assertThat(checking).isNull();
	}
}
