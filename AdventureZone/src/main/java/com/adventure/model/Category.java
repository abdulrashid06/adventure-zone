package com.adventure.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Comparable<Category> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@NotNull(message = "category name is mandatory")
	@NotBlank
	private String catName;
	
	@Column(nullable = false)
	private boolean isDeleted;
	
	@OneToMany(mappedBy = "category")
	private List<Activity> activities = new ArrayList<>();
	
	@OneToMany(mappedBy = "category")
	private List<Ticket>  ticket;

	@Override
    public int compareTo(Category otherCategory) {
        // Compare the category names in ascending order
        return this.catName.compareTo(otherCategory.catName);
    }
}
