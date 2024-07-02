package com.zigato.service;

import java.util.List;

import com.zigato.model.Category;
import com.zigato.model.Food;
import com.zigato.model.Restaurent;
import com.zigato.request.CreateFoodReq;

public interface FoodService {

	public Food createFood(CreateFoodReq req, Category category, Restaurent rest);

	public void deleteFood(long foodid) throws RuntimeException;

	public List<Food> getFoodByRest(long restid, boolean isVeg, boolean isNonVeg, boolean isSeasonal,
			String foodCategory);

	public List<Food> searchFood(String keyword);

	public Food findFoodById(long foodid) throws RuntimeException;

	public Food updateFoodAvailablityStatus(long foodId) throws RuntimeException;

}
