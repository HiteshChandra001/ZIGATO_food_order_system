package com.zigato.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.zigato.dto.RestaurentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String fullName;
	
	private String email;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	private String password;
	
	private ROLE role;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="customer")
	private List<Orders> orders = new ArrayList<>();
	
	@ElementCollection
	private List<RestaurentDTO> favorites = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Address> adresses = new ArrayList<>();
	
}
