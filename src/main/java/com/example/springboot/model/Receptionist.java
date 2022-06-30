package com.example.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="receptionist_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="receptionist",sequenceName="receptionist_gene",initialValue=4000)
public class Receptionist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="receptionist")
	@Column(name="receptionist_id")
	private long receptionistId;
	
	@Column(name="first_name")
	@NotEmpty
	@Size(min=4,message="firstName should contain atleast 4 letters in it")
	public String firstName;
	
	@Column(name="last_name")
	@NotEmpty                                                                         
	@Size(min=4,message="lastName should contain atleast 4 letters in it")
	public String lastName;
	
	@Column(name="email_id",unique=true)
	@NotEmpty
	@Email(message="Email  is not valid!")
	public String receptionistEmailId;
	
	@Column(name="passWord")
	@NotEmpty
	@Size(min=8, message="Password length must be 8 and contain uppercase,lowercase,digits")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	public String receptionistPassword;
	
	@Column(name="age")
	@NotNull
	public int age;
	
	@Column(name="gender")
	@NotEmpty
	@Size(min=4,message="please enter gender as male , female or not disclosed")
	public String gender;
	
	@Column(name="contact_number")
	@NotEmpty
	@Size(min=5,message="please enter a valid contact number")
	public String contactNumber;
	
	@Column(name="address")
	@NotEmpty
	@Size(min=3 , message="address must contain atleast 3 characters")
    public String address;
	
}