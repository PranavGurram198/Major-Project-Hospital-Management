package com.example.springboot.model;

 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
 
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table(name="Prescription_table")
@Data
@SequenceGenerator(name="prescription",sequenceName="prescription_gene",initialValue=6000)
public class Prescription {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="prescription")
	@Column(name="prescription_id")
	public long prescriptionId;
	
	
	@OneToOne( cascade=CascadeType.MERGE) 
	@JoinColumn(name="appointment_id")
	@JsonIgnore
	@OnDelete(action=OnDeleteAction.CASCADE) 
	public BookingAppointment BookingAppointment;
	 
	@Column(name="patient_id")
	public long patientId;

	@Column(name="doctor_id")
	public long doctorId;
	
	@Column(name="status")
	@NotEmpty
	public String status;
	
	 
	 
}
