package com.zigato.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	private User owner;

	private String name;

	private String description;

	private String cuisineType;

	@OneToOne
	private Address address;

	@Embedded
	private ContactInfo contactInfo;

	private String openingHours;

	@Builder.Default
	@OneToMany(mappedBy = "restaurent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Orders> orders = new ArrayList<>();

	@ElementCollection
	@Column(length=1000)
	private List<String> images;
	
	private LocalDateTime registrationDate;
	
	private boolean open;
	
	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "restaurent", cascade = CascadeType.ALL)
	private List<Food> foods = new ArrayList<>();
	
}
