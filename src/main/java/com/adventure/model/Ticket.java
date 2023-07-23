package com.adventure.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketId;
	
	private double price;
	
	@CreationTimestamp
	@FutureOrPresent(message = "Booking Date ")
	@Column(updatable = false)
	private LocalDateTime bookingDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDT;
	
	@Column(nullable = false)
	private boolean isExpired;
	

	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="categoryId")
	private List<Category>  category;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
	private Customer customer;

}
