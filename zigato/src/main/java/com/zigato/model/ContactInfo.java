package com.zigato.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ContactInfo {
	private String email;
	private String mobile;
	private String twitter;
	private String instagram;
}
