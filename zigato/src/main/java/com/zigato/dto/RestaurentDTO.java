package com.zigato.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RestaurentDTO {
	private long id;
	
	private String title;

	@Column(length = 1000)
	private List<String> images;

	private String description;

}
