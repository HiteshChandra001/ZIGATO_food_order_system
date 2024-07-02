package com.zigato.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigato.model.Category;
import com.zigato.model.Restaurent;
import com.zigato.repository.CategoryRepo;
import com.zigato.repository.RestaurentRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private RestaurentRepo restaurentRepo;

	@Override
	public Category createCategory(String name, long userid) {
		Restaurent restaurent = restaurentRepo.findByOwnerId(userid)
				.orElseThrow(() -> new RuntimeException("invalid userid for restaurent"));

		Category categ = Category.builder().name(name).restaurent(restaurent).build();

		return categoryRepo.save(categ);
	}

	@Override
	public List<Category> findCategByUserId(long uid) {

		Restaurent rest = restaurentRepo.findByOwnerId(uid)
				.orElseThrow(() -> new RuntimeException("invalid userid for restaurent"));

		restaurentRepo.findById(rest.getId()).orElseThrow(() -> new RuntimeException("invalid rid for restaurent"));
		List<Category> list = categoryRepo.findByRestaurentId(rest.getId());
		return list;
	}

	@Override
	public Category findCategById(long id) {
		Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("invalid category id"));
		return category;
	}

}
