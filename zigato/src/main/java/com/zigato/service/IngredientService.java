package com.zigato.service;

import java.util.List;

import com.zigato.model.IngredientCategory;
import com.zigato.model.IngredientItem;

public interface IngredientService {
	public IngredientCategory createIngredCateg(String name, long restid);

	public IngredientCategory findByCategId(long id);

	public List<IngredientCategory> findIngredCatgByRestId(long id);

	public IngredientItem createIngredientItem(long restId, String name, long catgId);

	public IngredientItem updateStock(long id);

	public List<IngredientItem> findIngredItemByRestId(long id);
}