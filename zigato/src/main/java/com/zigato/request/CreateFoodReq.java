package com.zigato.request;

import java.util.List;

import com.zigato.model.Category;
import com.zigato.model.IngredientItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFoodReq {

	private String name, description;
	private long price;
	private Category category;
	private List<String> images;
	private long restId;
	private boolean veg;
	private boolean seasional;
	private List<IngredientItem> ingredients;

}
