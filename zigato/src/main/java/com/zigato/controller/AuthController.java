package com.zigato.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigato.config.JwtProvider;
import com.zigato.model.Cart;
import com.zigato.model.User;
import com.zigato.repository.CartRepo;
import com.zigato.repository.UserRepo;
import com.zigato.request.LoginReq;
import com.zigato.response.AuthResponse;
import com.zigato.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private CartRepo cartRepo;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUser(@RequestBody User user) {

		Optional<User> findByEmail = userRepo.findByEmail(user.getEmail());
		if (findByEmail.isPresent())
			throw new RuntimeException("Email is Already Used");

		user.setPassword(encoder.encode(user.getPassword()));
		User saveUser = userRepo.save(user);

		Cart cart = new Cart();
		cart.setCustomer(saveUser);
		cartRepo.save(cart);

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse(jwt, "register", saveUser.getRole());

		return ResponseEntity.ok(authResponse);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginReq loginReq) {
		
		Authentication authentication = authenticate(loginReq.getEmail(), loginReq.getPassword());
		String jwt = jwtProvider.generateToken(authentication);

		User user = userRepo.findByEmail(loginReq.getEmail()).get();
		AuthResponse authResponse = new AuthResponse(jwt, "register", user.getRole());

		return ResponseEntity.ok(authResponse);
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
		if (userDetails == null) {
			throw new BadCredentialsException("invalid username and password");
		}

		if (!encoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid password");
		}

		return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());

	}

}
