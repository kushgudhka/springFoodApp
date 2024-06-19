package spring.kush.springCore.dto;

import lombok.Data;

@Data
public class FoodAppDTO {

    private Long id;
    private String itemName;
    private int price;
    private String typeOfFood;
    private  Boolean selfPickUp;

    public FoodAppDTO(){}
}
