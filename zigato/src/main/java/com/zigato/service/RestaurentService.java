package com.zigato.service;

import java.util.List;

import com.zigato.dto.RestaurentDTO;
import com.zigato.model.Restaurent;
import com.zigato.model.User;
import com.zigato.request.CreateRestReq;

public interface RestaurentService {

	public Restaurent createRestaurent(CreateRestReq req, User user) throws RuntimeException;

	public Restaurent updateRestaurent(long restId, CreateRestReq req) throws RuntimeException;

	public void deleteRestaurent(long restId) throws RuntimeException;

	public List<Restaurent> getAllRestaurent();

	public List<Restaurent> searchRestaurent(String keyword);

	public Restaurent findRestaurentById(long id) throws RuntimeException;

	public Restaurent findRestByUserid(long userid) throws RuntimeException;

	public RestaurentDTO addToFav(long restId,User user) throws RuntimeException;

	public Restaurent updateRestStatus(long restId) throws RuntimeException;
}
