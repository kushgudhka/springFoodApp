package spring.kush.springCore.mapper;

import org.springframework.stereotype.Component;
import spring.kush.springCore.dto.FoodAppDTO;
import spring.kush.springCore.entity.FoodAppEntity;

@Component
public class FoodAppMapper {

    public FoodAppEntity mapToEntity(FoodAppDTO foodAppDTO){

        FoodAppEntity foodAppEntity = new FoodAppEntity();

        foodAppEntity.setId(foodAppDTO.getId());
        foodAppEntity.setItemName(foodAppDTO.getItemName());
        foodAppEntity.setTypeOfFood(foodAppDTO.getTypeOfFood());
        foodAppEntity.setPrice(foodAppDTO.getPrice());
        foodAppEntity.setSelfPickUp(foodAppDTO.getSelfPickUp());

        return foodAppEntity;
    }

    public FoodAppDTO mapToVo(FoodAppEntity foodAppEntity){

        FoodAppDTO foodAppDTO = new FoodAppDTO();

        foodAppDTO.setId(foodAppEntity.getId());
        foodAppDTO.setItemName(foodAppEntity.getItemName());
        foodAppDTO.setTypeOfFood(foodAppEntity.getTypeOfFood());
        foodAppDTO.setPrice(foodAppEntity.getPrice());
        foodAppDTO.setSelfPickUp(foodAppEntity.getSelfPickUp());

        return foodAppDTO;

    }
}
