package spring.kush.springCore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.kush.springCore.dto.FoodAppDTO;
import spring.kush.springCore.dto.ResponseDTO;
import spring.kush.springCore.service.FoodAppService;

import java.util.List;

@RestController
@RequestMapping("/foodItem")
@Slf4j
public class FoodAppController {

    final
    FoodAppService foodAppService;

    public FoodAppController(FoodAppService foodAppService) {
        this.foodAppService = foodAppService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody FoodAppDTO foodAppDTO) {
        try{
            foodAppService.saveFoodItems(foodAppDTO);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK,"Food Item Saved Successfully"), HttpStatus.OK);
        }catch (NullPointerException e){
            log.info(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST,"Food Item Saving Failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public List<FoodAppDTO> getAllItems() {
        try{
            return foodAppService.getAllItems();
        }catch (NullPointerException e){
            log.info(e.getMessage());
            throw new NullPointerException("Empty Menu");
        }

    }

    @GetMapping("/{id}")
    public FoodAppDTO getFoodById(@PathVariable Long id) {
        try{
            return foodAppService.getFoodById(id);
        }catch (NullPointerException e){
            log.info(e.getMessage());
            throw new NullPointerException("Id not found");
        }
    }

    @GetMapping("/name/{name}")
    public List<FoodAppDTO> getFoodByName(@PathVariable String name) {
        try{
            return foodAppService.getFoodByName(name);
        }catch (NullPointerException e){
            log.info(e.getMessage());
            throw new NullPointerException("Food Item Name Not Found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteFoodById(@PathVariable Long id) {
        try{
            log.info("INSIDE DELETE CONTROLLER");
            foodAppService.deleteFoodById(id);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK,"FOOD ID DELETED SUCCESSFULLY"),HttpStatus.OK);
        }catch (Exception e){
            log.info("DELETION FAILED FOR ID : {}",id);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND,"FOOD ID NOT FOUND."),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateFoodById(@PathVariable Long id, @RequestBody FoodAppDTO foodAppDTO) {
        try {
            log.info("INSIDE UPDATE CONTROLLER");
            foodAppService.updateFoodItemsById(foodAppDTO);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK,"FOOD ID UPDATED SUCCESSFULLY"),HttpStatus.OK);
        }catch (Exception e){
            log.info("UPDATE FAILED FOR ID : {}",id);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.FORBIDDEN,"ERROR WHILE UPDATING FOOD ITEM"),HttpStatus.FORBIDDEN);
        }
    }
}
