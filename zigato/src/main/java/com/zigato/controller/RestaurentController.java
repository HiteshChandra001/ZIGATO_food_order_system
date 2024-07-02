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

import com.zigato.dto.RestaurentDTO;
import com.zigato.model.Restaurent;
import com.zigato.model.User;
import com.zigato.service.RestaurentService;
import com.zigato.service.UserService;

@RestController
@RequestMapping("/api/restaurent")
public class RestaurentController {
	@Autowired
	private RestaurentService restService;
	@Autowired
	private UserService userService;

	@GetMapping("/search")
	public ResponseEntity<List<Restaurent>> searchRest(@RequestHeader("Authorization") String jwt,
			@RequestParam String keyword) {

		List<Restaurent> list = restService.searchRestaurent(keyword);
		return ResponseEntity.ok(list);
	}

	@GetMapping
	public ResponseEntity<List<Restaurent>> getAllRest(@RequestHeader("Authorization") String jwt) {

		List<Restaurent> list = restService.getAllRestaurent();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurent> findRestById(@PathVariable long id, @RequestHeader("Authorization") String jwt) {

		Restaurent rest = restService.findRestaurentById(id);
		return ResponseEntity.ok(rest);
	}

	@GetMapping("/{id}/add-fav")
	public ResponseEntity<RestaurentDTO> addToFav(@PathVariable long id, @RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwtToken(jwt);
		RestaurentDTO dto = restService.addToFav(id, user);

		return ResponseEntity.ok(dto);
	}
}
