package com.zigato.service;

import java.util.List;

import com.zigato.model.Category;

public interface CategoryService {

	public Category createCategory(String name, long userid);

	public List<Category> findCategByRestId(long rid);

	public Category findCategById(long id);
}
