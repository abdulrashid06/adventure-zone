package com.adventure.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityId;
	
	@NotNull(message = "activity name is mandatory")
	@Column(nullable = false, unique = true)
	private String activityName;
	
	@NotNull(message = "charges name is mandatory")
	private double charges;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(nullable = false)
	private boolean isDeleted;
	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
	private Category category;

	
}
