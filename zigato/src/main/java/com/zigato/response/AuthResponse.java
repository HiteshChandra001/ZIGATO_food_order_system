package com.zigato.response;

import com.zigato.model.ROLE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	private String jwt;
	private String message;
	private ROLE role;
}
