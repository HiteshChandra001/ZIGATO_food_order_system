package com.zigato.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String description;

	private long price;

	@ManyToOne
	private Category category;

	@Column(length = 1000)
	@ElementCollection
	private List<String> images;

	private boolean available;

	@ManyToOne
	private Restaurent restaurent;

	private boolean isVeg;

	private boolean isSeasonal;

	@Builder.Default
	@ManyToMany
	private List<IngredientItem> ingredients = new ArrayList<>();
	
	private Date createdAt;
	
}
