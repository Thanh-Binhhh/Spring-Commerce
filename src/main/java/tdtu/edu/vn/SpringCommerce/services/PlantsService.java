package tdtu.edu.vn.SpringCommerce.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.SpringCommerce.models.Plant;
import tdtu.edu.vn.SpringCommerce.repositories.PlantsRepository;
import tdtu.edu.vn.SpringCommerce.repositories.PlantsSpecification;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantsService {
    final PlantsRepository plantsRepository;

    public Page<Plant> getPlants(int currentPage) {
        int pageSize = 12;
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return plantsRepository.findAll(pageable);
    }

    public Optional<Plant> getPlant(int id) {
        return plantsRepository.findById(id);
    }

    public List<Plant> getPlantsByIds(List<Integer> ids) {
        return plantsRepository.findAllById(ids);
    }

    public int[] getPlantsPrice() {
        int minPrice = plantsRepository.findTopByOrderByPriceAsc().getPrice();
        int maxPrice = plantsRepository.findTopByOrderByPriceDesc().getPrice();
        return new int[]{minPrice, maxPrice};
    }

    public List<Plant> filterPlants(String keyword, String category, String size, String characteristic, Integer maxPrice) {
        Specification<Plant> spec = Specification
                .where(PlantsSpecification.hasNameContaining(keyword))
                .and(PlantsSpecification.hasCategory(category))
                .and(PlantsSpecification.hasSize(size))
                .and(PlantsSpecification.hasCharacteristic(characteristic))
                .and(PlantsSpecification.hasPriceLessThanEqual(maxPrice));

        return plantsRepository.findAll(spec);
    }
}
