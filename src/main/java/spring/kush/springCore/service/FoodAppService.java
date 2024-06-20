package spring.kush.springCore.service;

import spring.kush.springCore.dto.FoodAppDTO;

import java.util.List;

public interface FoodAppService {

	void saveFoodItems(FoodAppDTO foodAppDTO) throws NullPointerException;

	void saveMultipleFoodItems(List<FoodAppDTO> foodAppDTO) throws NullPointerException;

	List<FoodAppDTO> getAllItems() throws NullPointerException;

	FoodAppDTO getFoodById(Long id) throws NullPointerException;

	List<FoodAppDTO> getFoodByName(String itemName) throws NullPointerException;

	void deleteFoodById(Long id) throws NullPointerException;

	void updateFoodItemsById(FoodAppDTO foodAppDTO) throws NullPointerException;

}
