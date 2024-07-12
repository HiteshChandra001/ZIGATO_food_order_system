package com.zigato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zigato.model.Food;
import com.zigato.service.FoodService;

@RestController
@RequestMapping("/api/food")
public class FoodController {
	@Autowired
	private FoodService foodService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
			@RequestHeader("Authorization") String jwt) {

		List<Food> list = foodService.searchFood(name);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/restaurent/{rid}")
	public ResponseEntity<List<Food>> getFoodByRest(@PathVariable int rid, @RequestParam String category,
			@RequestParam boolean isVeg, @RequestParam boolean isSeasonal, @RequestParam boolean isNonVeg,
			@RequestHeader("Authorization") String jwt) {

		List<Food> list = foodService.getFoodByRest(rid, isVeg, isNonVeg, isSeasonal, category);
		return ResponseEntity.ok(list);
	}

}
