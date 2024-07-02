package com.zigato.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.zigato.dto.RestaurentDTO;
import com.zigato.model.Address;
import com.zigato.model.Restaurent;
import com.zigato.model.User;
import com.zigato.repository.AddressRepo;
import com.zigato.repository.RestaurentRepo;
import com.zigato.repository.UserRepo;
import com.zigato.request.CreateRestReq;

@Service
public class RestaurentServiceImpl implements RestaurentService {

	@Autowired
	private RestaurentRepo restRepo;
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	public Restaurent createRestaurent(CreateRestReq req, User user) throws RuntimeException {

		Address address = addressRepo.save(req.getAddress());

		Restaurent restaurent = Restaurent.builder().address(address).contactInfo(req.getContactInfo())
				.cuisineType(req.getCuisineType()).description(req.getDescription()).images(req.getImages())
				.name(req.getName()).openingHours(req.getOpeningHours()).registrationDate(LocalDateTime.now())
				.open(true).owner(user).build();

		return restRepo.save(restaurent);
	}

	@Override
	public Restaurent updateRestaurent(long restId, CreateRestReq req) throws RuntimeException {
		Restaurent restaurent = restRepo.findById(restId)
				.orElseThrow(() -> new BadCredentialsException("invalid restaurent id"));

		if (req.getName() != null)
			restaurent.setName(req.getName());
		if (req.getDescription() != null)
			restaurent.setDescription(req.getDescription());
		if (req.getCuisineType() != null)
			restaurent.setCuisineType(req.getCuisineType());

		return restRepo.save(restaurent);
	}

	@Override
	public void deleteRestaurent(long restId) throws RuntimeException {
		Restaurent restaurent = restRepo.findById(restId)
				.orElseThrow(() -> new BadCredentialsException("invalid restaurent id"));

		restRepo.delete(restaurent);
	}

	@Override
	public List<Restaurent> getAllRestaurent() {
		return restRepo.findAll();
	}

	@Override
	public List<Restaurent> searchRestaurent(String keyword) {
		return restRepo.findBySearchQuery(keyword);
	}

	@Override
	public Restaurent findRestaurentById(long id) throws RuntimeException {
		Restaurent rest = restRepo.findById(id).orElseThrow(() -> new BadCredentialsException("invalid rest id"));
		return rest;
	}

	@Override
	public Restaurent findRestByUserid(long userid) throws RuntimeException {
		Restaurent rest = restRepo.findByOwnerId(userid)
				.orElseThrow(() -> new BadCredentialsException("invalid owner id"));
		return rest;
	}

	@Override
	public RestaurentDTO addToFav(long restId, User user) throws RuntimeException {
		Restaurent rest = restRepo.findById(restId).orElseThrow(() -> new BadCredentialsException("invalid rest id"));
		System.out.println(rest);
		RestaurentDTO dto = new RestaurentDTO(restId, rest.getName(), rest.getImages(), rest.getDescription());

		boolean flag = user.getFavorites().stream().anyMatch(r -> r.getId() == restId);
		if (flag)
			user.getFavorites().removeIf(r -> r.getId() == restId);
		else
			user.getFavorites().add(dto);

		userRepo.save(user);
		return dto;
	}

	@Override
	public Restaurent updateRestStatus(long restId) throws RuntimeException {
		Restaurent rest = restRepo.findById(restId).orElseThrow(() -> new BadCredentialsException("invalid rest id"));

		rest.setOpen(!rest.isOpen());
		return restRepo.save(rest);
	}

}
