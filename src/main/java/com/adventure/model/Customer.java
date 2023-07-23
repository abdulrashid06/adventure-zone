package com.adventure.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
public class Customer extends AbtractUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	//@NotBlank(message = "age is mandatory")
	private int age;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
private List<Ticket> ticketList=new ArrayList<>();



	public Customer(Integer userId, String username, String password, String address, String mobNumber, String email,
			LocalDateTime createdDate, LocalDateTime updatedDT, boolean isDeleted, LocalDateTime deleteDT, String role
			, @NotBlank(message = "age is mandatory") int age) {
		super(userId, username, password, address, mobNumber, email, createdDate, updatedDT, isDeleted, deleteDT, role);
//		this.customerId = customerId;
		this.age = age;
	}



}
