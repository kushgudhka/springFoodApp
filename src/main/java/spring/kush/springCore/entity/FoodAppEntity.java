package spring.kush.springCore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class FoodAppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String itemName;

    @NotNull
    private int price;

    @NotNull
    private String typeOfFood;

    @NotNull
    private Boolean selfPickUp;
}
