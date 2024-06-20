package spring.kush.springCore.dto;

import lombok.Data;

@Data
public class FoodAppDTO {

	private Long id;

	private String itemName;

	private int price;

	private String choice;

	private Boolean selfPickUp;

	private String typeOfFood;

	public FoodAppDTO() {
	}

}
