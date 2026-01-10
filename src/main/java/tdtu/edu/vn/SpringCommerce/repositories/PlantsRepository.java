package tdtu.edu.vn.SpringCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tdtu.edu.vn.SpringCommerce.models.Plant;

public interface PlantsRepository extends JpaRepository<Plant, Integer>, JpaSpecificationExecutor<Plant> {

    Plant findTopByOrderByPriceDesc();
    Plant findTopByOrderByPriceAsc();
}
