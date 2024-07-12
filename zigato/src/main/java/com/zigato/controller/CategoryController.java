package com.zigato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigato.model.Category;
import com.zigato.model.User;
import com.zigato.service.CategoryService;
import com.zigato.service.UserService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;

	@PostMapping("/admin/category")
	public ResponseEntity<Category> createCateg(@RequestBody Category category,
			@RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwtToken(jwt);

		Category categ = categoryService.createCategory(category.getName(), user.getId());

		return ResponseEntity.ok(categ);
	}  

	@GetMapping("/category/restaurent")
	public ResponseEntity<List<Category>> getRestCategs(@RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwtToken(jwt);

		List<Category> list = categoryService.findCategByUserId(user.getId());
		return ResponseEntity.ok(list);
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCateg(@PathVariable int id, @RequestHeader("Authorization") String jwt) {
		Category categ = categoryService.findCategById(id);
		return ResponseEntity.ok(categ);
	}

}
