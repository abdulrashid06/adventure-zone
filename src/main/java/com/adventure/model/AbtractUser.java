package com.adventure.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class AbtractUser {
	
	@Min(value = 1)
	@NotNull(message = "user id is mandatory")
	private Integer userId;
	
	@NotNull(message = "username is mandatory")
	@NotBlank
	private String username;
	
	@NotNull(message = "password is mandatory")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	//@Size(min = 4 , max = 8, message = "password must be between 4-8 alphanumeric")
	private String password;
	
	@NotBlank
	@NotNull(message = "address is mandatory")
	private String address;
	
	@NotNull(message = "mobile number is mandatory")
	@Pattern(regexp = "^[6-9]\\d{9}")
	private String mobNumber;
	
	@Email(message = "please provide a valid email")
	@Column(unique = true, nullable = false)
	@NotNull(message = "email id is mandatory")
	private String email;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate=LocalDateTime.now();
	
	@Column(nullable = true)
	@UpdateTimestamp
	private LocalDateTime updatedDT;
	
	//@Column(nullable = true)
	private boolean isDeleted=false;
	
	@Future
	@Column(nullable = true)
	private LocalDateTime deleteDT;
	
	@NotNull(message = "role is mandatory")
	@NotBlank
	private String role;

}
