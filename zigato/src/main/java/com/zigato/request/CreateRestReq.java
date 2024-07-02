package com.zigato.request;

import java.util.List;

import com.zigato.model.Address;
import com.zigato.model.ContactInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRestReq {
	private long id;
	private String name,description,cuisineType,openingHours;
	private Address address;
	private ContactInfo contactInfo;
	private List<String> images;
}
