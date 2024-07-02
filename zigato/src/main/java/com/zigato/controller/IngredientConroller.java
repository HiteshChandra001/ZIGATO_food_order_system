package com.zigato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigato.model.IngredientCategory;
import com.zigato.model.IngredientItem;
import com.zigato.request.createIngredCatgReq;
import com.zigato.request.createIngredItemReq;
import com.zigato.service.IngredientService;

@RestController
@RequestMapping("/api/admin/ingredient")
public class IngredientConroller {
	@Autowired
	private IngredientService ingredientService;

	@PostMapping("/category")
	public ResponseEntity<IngredientCategory> createIngredCatg(@RequestBody createIngredCatgReq req) {
		IngredientCategory categ = ingredientService.createIngredCateg(req.getName(), req.getRestId());
		return ResponseEntity.ok(categ);
	}

	@PostMapping
	public ResponseEntity<IngredientItem> createIngredItem(@RequestBody createIngredItemReq req) {
		IngredientItem item = ingredientService.createIngredientItem(req.getRestId(), req.getName(), req.getCatgId());
		return ResponseEntity.ok(item);
	}
	
	@PutMapping("/{id}/stock")
	public ResponseEntity<IngredientItem> updateIngredStock(@PathVariable long id) {
		IngredientItem item = ingredientService.updateStock(id);
		return ResponseEntity.ok(item);
	}
	
	@GetMapping("/restaurent/{id}")
	public ResponseEntity<List<IngredientItem>> getRestaurentIngred(@PathVariable long id) {
		List<IngredientItem> list = ingredientService.findIngredItemByRestId(id);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/restaurent/{id}/category")
	public ResponseEntity<List<IngredientCategory>> getRestaurentIngredCateg(@PathVariable long id) {
		List<IngredientCategory> list = ingredientService.findIngredCatgByRestId(id);
		return ResponseEntity.ok(list);
	}

}
