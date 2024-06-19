package spring.kush.springCore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.kush.springCore.entity.FoodAppEntity;

import java.util.List;

public interface FoodAppRepository extends JpaRepository<FoodAppEntity,Long> {
    List<FoodAppEntity> findByItemName(String name);
}
