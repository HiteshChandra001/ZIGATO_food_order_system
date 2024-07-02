package com.zigato.request;

import lombok.Data;

@Data
public class UpdateCartItemReq {
	private long cartItemId;
	private int quantity;
}
