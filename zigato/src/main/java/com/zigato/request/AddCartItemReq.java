package com.zigato.request;

import java.util.List;

import lombok.Data;

@Data
public class AddCartItemReq {
	private long foodId;
	private int quantity;
	private List<String> ingredients;
}
