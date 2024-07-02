package com.zigato.request;

import lombok.Data;

@Data
public class createIngredItemReq {
	private String name;
	private long restId, catgId;
}
