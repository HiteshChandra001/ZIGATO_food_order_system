package com.zigato.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigato.model.Category;
import com.zigato.model.Food;
import com.zigato.model.Restaurent;
import com.zigato.repository.FoodRepo;
import com.zigato.request.CreateFoodReq;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepo foodRepo;

	@Override
	public Food createFood(CreateFoodReq req, Category category, Restaurent rest) {
		Food food = Food.builder().name(req.getName()).category(req.getCategory()).restaurent(rest)
				.images(req.getImages()).description(req.getDescription()).price(req.getPrice())
				.ingredients(req.getIngredients()).isSeasonal(req.isSeasional()).isVeg(req.isVeg())
				.images(req.getImages()).available(true).build();
		Food savedFood = foodRepo.save(food);
		rest.getFoods().add(savedFood);

		return savedFood;
	}

	@Override
	public void deleteFood(long foodid) throws RuntimeException {

		Food food = foodRepo.findById(foodid).orElseThrow(() -> new RuntimeException("invalid food id"));
		food.setRestaurent(null);
		foodRepo.save(food);
	}

	@Override
	public List<Food> getFoodByRest(long restid, boolean isVeg, boolean isNonVeg, boolean isSeasonal,
			String foodCategory) {

		List<Food> foods = foodRepo.findByRestaurentId(restid);

		if (isVeg)
			foods = foods.stream().filter(f -> f.isVeg()).toList();
		if (isNonVeg)
			foods = foods.stream().filter(f -> f.isVeg()).toList();
		if (isSeasonal)
			foods = foods.stream().filter(f -> f.isVeg()).toList();
		if (foodCategory != null && foodCategory.length() > 0)
			foods = foods.stream().filter(f -> {
				if (f.getCategory() != null)
					return f.getCategory().getName().equals(foodCategory);
				return false;
			}).toList();

		return foods;
	}

	@Override
	public List<Food> searchFood(String keyword) {
		List<Food> list = foodRepo.searchFood(keyword);
		return list;
	}

	@Override
	public Food findFoodById(long foodid) throws RuntimeException {
		Food food = foodRepo.findById(foodid).orElseThrow(() -> new RuntimeException("invalid food id"));
		return food;
	}

	@Override
	public Food updateFoodAvailablityStatus(long foodId) throws RuntimeException {
		Food food = foodRepo.findById(foodId).orElseThrow(() -> new RuntimeException("invalid food id"));
		food.setAvailable(!food.isAvailable());
		return food;
	}

}
