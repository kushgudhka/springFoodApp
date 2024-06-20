package spring.kush.springCore.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.kush.springCore.dto.FoodAppDTO;
import spring.kush.springCore.entity.FoodAppEntity;
import spring.kush.springCore.mapper.FoodAppMapper;
import spring.kush.springCore.repository.FoodAppRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class FoodAppServiceImpl implements FoodAppService {

	private final FoodAppRepository foodAppRepository;

	@Autowired
	public FoodAppServiceImpl(FoodAppRepository foodAppRepository) {
		this.foodAppRepository = foodAppRepository;
	}

	@Autowired
	private FoodAppMapper foodAppMapper;

	@Override
	public void saveFoodItems(FoodAppDTO foodAppDTO) throws NullPointerException {

		if (foodAppDTO == null) {
			log.info("ENTER DATA TO SAVE");
			throw new NullPointerException("NO DATA TO SAVE");
		}

		if (!foodAppDTO.getItemName().isBlank() && !foodAppDTO.getItemName().isEmpty()
				&& !foodAppDTO.getChoice().isBlank() && !foodAppDTO.getChoice().isEmpty()
				&& !foodAppDTO.getTypeOfFood().isBlank() && !foodAppDTO.getTypeOfFood().isEmpty()
				&& foodAppDTO.getPrice() != 0) {
			FoodAppEntity foodAppEntity = foodAppMapper.mapToEntity(foodAppDTO);
			foodAppRepository.save(foodAppEntity);
			log.info("SUCCESS");
		}
		else {
			throw new NullPointerException("ID, FoodItemName, Price, or TypeOfFood is blank or empty");
		}

	}

	@Override
	public void saveMultipleFoodItems(List<FoodAppDTO> foodAppDTO) throws NullPointerException {

		if (foodAppDTO == null) {
			log.info("ENTER DATA TO SAVE");
			throw new NullPointerException("NO DATA TO SAVE");
		}

		if (!foodAppDTO.isEmpty() && foodAppDTO.size() > 1) {
			List<FoodAppEntity> foodItems = foodAppDTO.stream()
				.map(foodAppMapper::mapToEntity)
				.collect(Collectors.toList());
			foodAppRepository.saveAll(foodItems);
			log.info("SUCCESS");
		}
		else {
			log.info("NO DATA TO SAVE");
		}

	}

	@Override
	public List<FoodAppDTO> getAllItems() {
		List<FoodAppEntity> foodAppEntities = foodAppRepository.findAll();
		return foodAppEntities.stream().map(foodAppMapper::mapToVo).collect(Collectors.toList());
	}

	@Override
	public FoodAppDTO getFoodById(Long id) throws NullPointerException {

		if (!foodAppRepository.existsById(id) || foodAppRepository.findById(id).isEmpty()) {
			throw new NullPointerException("ID NOT FOUND");
		}
		FoodAppEntity foodAppEntity = foodAppRepository.findById(id).orElse(null);
		return foodAppEntity != null ? foodAppMapper.mapToVo(foodAppEntity) : null;
	}

	@Override
	public List<FoodAppDTO> getFoodByName(String name) {
		List<FoodAppEntity> foodAppEntity = foodAppRepository.findByItemName(name);
		if (foodAppRepository.findByItemName(name).isEmpty() || foodAppEntity.isEmpty()) {
			throw new NullPointerException("FOOD NOT FOUND");
		}

		return foodAppEntity.stream().map(foodAppMapper::mapToVo).collect(Collectors.toList());
	}

	@Override
	public void deleteFoodById(Long id) throws NullPointerException {
		if (!foodAppRepository.existsById(id) || foodAppRepository.findById(id).isEmpty()) {
			log.error("{} ID NOT FOUND", id);
			throw new NullPointerException("ID NOT FOUND");
		}

		try {
			FoodAppEntity foodAppEntity = foodAppRepository.findById(id).orElse(null);

			foodAppRepository.deleteById(foodAppEntity.getId());
			log.info("SUCCESSFULLY DELETE FOOD ID: {}", id);

			foodAppMapper.mapToVo(foodAppEntity);
		}
		catch (Exception e) {
			log.error("ERROR DELETING FOOD WITH ID {}", id);
			throw new NullPointerException("FOOD NOT FOUND");
		}

	}

	@Override
	public void updateFoodItemsById(FoodAppDTO foodAppDTO) {
		if (!foodAppRepository.existsById(foodAppDTO.getId())
				|| foodAppRepository.findById(foodAppDTO.getId()).isEmpty()) {
			throw new NullPointerException("ID NOT FOUND");
		}
		try {
			FoodAppEntity foodAppEntity = foodAppRepository.findById(foodAppDTO.getId()).orElse(null);

			foodAppEntity.setId(foodAppDTO.getId());
			foodAppEntity.setItemName(foodAppDTO.getItemName());
			foodAppEntity.setChoice(foodAppDTO.getChoice());
			foodAppEntity.setPrice(foodAppDTO.getPrice());
			foodAppEntity.setSelfPickUp(foodAppDTO.getSelfPickUp());
			foodAppEntity.setTypeOfFood(foodAppDTO.getTypeOfFood());

			foodAppRepository.save(foodAppEntity);
			log.info("SUCCESSFULLY UPDATE FOOD ID: {}", foodAppDTO.getId());

			foodAppMapper.mapToVo(foodAppEntity);
		}
		catch (Exception e) {
			log.error("ERROR UPDATING FOOD WITH ID {}", foodAppDTO.getId());
			throw new NullPointerException("FOOD NOT FOUND");
		}
	}

}
