package com.zigato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigato.model.Restaurent;
import com.zigato.model.User;
import com.zigato.request.CreateRestReq;
import com.zigato.response.MessageResponse;
import com.zigato.service.RestaurentService;
import com.zigato.service.UserService;

@RestController
@RequestMapping("/api/admin/restaurents")
public class AdminRestaurentController {

	@Autowired
	private RestaurentService restService;
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Restaurent> createRest(@RequestBody CreateRestReq req,
			@RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwtToken(jwt);
		Restaurent rest = restService.createRestaurent(req, user);
		return ResponseEntity.ok(rest);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurent> updateRest(@RequestBody CreateRestReq req,
			@RequestHeader("Authorization") String jwt, @PathVariable long id) {

		Restaurent rest = restService.updateRestaurent(id, req);
		return ResponseEntity.ok(rest);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteRest(@RequestHeader("Authorization") String jwt,
			@PathVariable long id) {
		restService.deleteRestaurent(id);

		MessageResponse mr = new MessageResponse("Restaurent Deleted Successfully");
		return ResponseEntity.ok(mr);
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<Restaurent> updateRestStatus(@RequestHeader("Authorization") String jwt,
			@PathVariable long id) {

		Restaurent rest = restService.updateRestStatus(id);

		return ResponseEntity.ok(rest);
	}

	@GetMapping("/user")
	public ResponseEntity<Restaurent> findRestByUserId(@RequestHeader("Authorization") String jwt) {
		User user = userService.findUserByJwtToken(jwt);
		Restaurent restaurent = restService.findRestByUserid(user.getId());

		return ResponseEntity.ok(restaurent);
	}

}
