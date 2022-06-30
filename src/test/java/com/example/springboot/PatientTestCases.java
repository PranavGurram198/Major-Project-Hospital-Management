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
import com.example.springboot.model.Patient;
import com.example.springboot.repository.AdminRepository;
import com.example.springboot.repository.PatientRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientTestCases {
	
	@Autowired
	private PatientRepository patientRepository;
	
	//test case for saving 
	@Test
	@Order(1)
	//@Disabled
	public void savePatient() {
		
		Patient patient = Patient.builder()
			.address("palani").patientEmailId("dhaarani@gmail.com")
			.patientPassword("Arav@3299").age(25).contactNumber("9976934899")
			.firstName("Surya").gender("female").lastName("Pakash").admitDate(null).dischargeDate(null)
			 .bloodGroup("o").medicalBackground("minu").doctorId(0).roomId(0).paymentStatus("ki").build();
		
		patientRepository.save(patient);
		
		Assertions.assertThat(patient.getPatientId()).isGreaterThan(0);
}
	
	
	@Test
	@Disabled
	@Order(2)
	public void loginPatient() {
		
		
		Optional<Patient> patient = patientRepository.findByPatientEmailIdAndPatientPassword("pranav@gmail.com", "Pranav@3299");
	
		Patient newPatient=null;
	
		if(patient.isPresent()) {
		newPatient = patient.get();
		}
	//if that newadmin is not null then it will runn otherwise it will fail
		Assertions.assertThat(newPatient).isNotNull();
	
}
	
	@Test
	@Order(3)
	//@Disabled
	public void updatePatient() {
										//optional
		Patient patient = patientRepository.findById(3000L).get();
	
		patient.setFirstName("Aravindh");
	
		Patient updatedPatient = patientRepository.save(patient);
	
		Assertions.assertThat(updatedPatient.firstName).isEqualTo("Aravindh");
	
	
		
	}
	
	@Test
@Order(4)
	@Disabled
	public void getAllPatients() {

		List<Patient> patients = patientRepository.findAll();
		//if the list size grator than zero then it will run 
	Assertions.assertThat(patients.size()).isGreaterThan(0);
}
	@Test
	@Order(5)
	@Disabled
	public void getPatientById() {
		
		Patient patient = patientRepository.findById(3000L).get();
		
	Assertions.assertThat(patient.getPatientId()).isEqualTo(3000L);
	}

	@Test
	@Order(6)
	public void deletePatient() {
		
		Patient patient = patientRepository.findById(3001L).get();
		
		patientRepository.delete(patient);
		//here i have deleted from the database
		
		Patient checking = null;
		
		Optional<Patient> afterDeleting = patientRepository.findById(3001L);
		
		if(afterDeleting.isPresent()) {
			checking=afterDeleting.get();
		}
		
		Assertions.assertThat(checking).isNull();
	}

}
