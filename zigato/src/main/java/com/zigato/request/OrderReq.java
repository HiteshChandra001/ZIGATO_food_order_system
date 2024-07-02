package com.zigato.request;

import com.zigato.model.Address;

import lombok.Data;

@Data
public class OrderReq {
	private long restId;
	private Address deliveryAddress;
}
