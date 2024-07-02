package com.zigato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigato.model.Food;
import com.zigato.model.Restaurent;
import com.zigato.request.CreateFoodReq;
import com.zigato.response.MessageResponse;
import com.zigato.service.FoodService;
import com.zigato.service.RestaurentService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
	@Autowired
	private FoodService foodService;
	@Autowired
	private RestaurentService restaurentService;

	@PostMapping
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodReq req, @RequestHeader("Authorization") String jwt) {

		Restaurent rest = restaurentService.findRestaurentById(req.getRestId());
		Food food = foodService.createFood(req, req.getCategory(), rest);
		return ResponseEntity.ok(food);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MessageResponse> updateFoodAvaiability(@PathVariable int id,
			@RequestHeader("Authorization") String jwt) {
		foodService.updateFoodAvailablityStatus(id);
		MessageResponse mr = new MessageResponse("Food updated Successfuly");
		return ResponseEntity.ok(mr);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable int id,
			@RequestHeader("Authorization") String jwt) {
		foodService.deleteFood(id);
		MessageResponse mr = new MessageResponse("Food Deleted Successfuly");
		return ResponseEntity.ok(mr);
	}

}
