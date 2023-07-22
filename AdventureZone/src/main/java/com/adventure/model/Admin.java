package com.adventure.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends AbtractUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer AdminId;

	public Admin(Integer userId, String username, String password, String address, String mobNumber, String email,
			LocalDateTime createdDate, LocalDateTime updatedDT, boolean isDeleted, LocalDateTime deleteDT, String role) {
		super(userId, username, password, address, mobNumber, email, createdDate, updatedDT, isDeleted, deleteDT, role);
//		AdminId = adminId;
	}
	
	

}
