package com.zigato.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigato.model.IngredientCategory;
import com.zigato.model.IngredientItem;
import com.zigato.model.Restaurent;
import com.zigato.repository.IngredientCategoryRepo;
import com.zigato.repository.IngredientItemRepo;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientItemRepo itemRepo;
	@Autowired
	private IngredientCategoryRepo categoryRepo;
	@Autowired
	private RestaurentService restaurentService;

	@Override
	public IngredientCategory createIngredCateg(String name, long restid) {
		Restaurent rest = restaurentService.findRestaurentById(restid);
		IngredientCategory ingredCatg = new IngredientCategory();
		ingredCatg.setName(name);
		ingredCatg.setRestaurent(rest);
		return categoryRepo.save(ingredCatg);
	}

	@Override
	public IngredientCategory findByCategId(long id) {
		IngredientCategory ingredCat = categoryRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("invalid ingredient category id"));
		return ingredCat;
	}

	@Override
	public List<IngredientCategory> findIngredCatgByRestId(long id) {
		restaurentService.findRestaurentById(id);
		return categoryRepo.findByRestaurentId(id);
	}

	@Override
	public IngredientItem createIngredientItem(long restId, String name, long catgId) {
		Restaurent rest = restaurentService.findRestaurentById(restId);
		IngredientCategory category = findByCategId(catgId);

		IngredientItem item = new IngredientItem();
		item.setName(name);
		item.setRestaurent(rest);
		item.setCategory(category);

		IngredientItem savedItem = itemRepo.save(item);
		category.getIngredients().add(savedItem);
		categoryRepo.save(category);
		return savedItem;
	}

	@Override
	public IngredientItem updateStock(long id) {
		IngredientItem item = itemRepo.findById(id).orElseThrow(() -> new RuntimeException("invalid item id"));
		item.setInStoke(!item.isInStoke());

		return itemRepo.save(item);
	}

	@Override
	public List<IngredientItem> findIngredItemByRestId(long id) {
		return itemRepo.findByRestaurentId(id);
	}

}
