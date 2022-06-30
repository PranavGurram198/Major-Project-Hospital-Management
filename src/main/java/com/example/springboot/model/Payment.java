package com.example.springboot.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Table(name="payment_table")
@SequenceGenerator(name="payment",sequenceName="gene",initialValue=8000)
@Data
public class Payment {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="payment")
	private long paymentId;
	
 
	@Column(name="total_payment")
	private  int totalPayment;

	@Column(name="room_id")
	private  long roomId;

	@Column(name="prescription_id",unique=true)
	private  long prescriptionId;

	@Column(name="admitDate")
	private Date admitDate;

	@Column(name="payment_Date")
	private Date paymentDate;

	@Column(name="payment_status")
	//@NotEmpty
	private String paymentStatus;

 	@Column(name="name_on_card") 
	//@NotEmpty
	//@Size(min=3 , message="name must contain atleast 3 characters")
	private String nameOnCard;

	@Column(name="card_number")
	//@NotEmpty
	//@Size(min=16 , max=16,message="cardNumber must contain 16 digits")
	private String cardNumber;

	@Column(name="exp_year")
	//@NotEmpty
	private String expYear;

	@Column(name="cvv")
	//@NotNull
	private int cvv;
	
	@Column(name="upi")
	 
	private String upi;

	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="patient_id")
	@JsonIgnore
	private Patient patient;

}
