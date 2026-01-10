package tdtu.edu.vn.SpringCommerce.repositories;

import org.springframework.data.jpa.domain.Specification;
import tdtu.edu.vn.SpringCommerce.models.Plant;

public class PlantsSpecification {

    public static Specification<Plant> hasCategory(String category) {
        return (
                root,
                query,
                cb) -> category == null ?
                                    null :
                                    cb.equal(root.get("category"), category);
    }

    public static Specification<Plant> hasSize(String size) {
        return (root, query, cb) -> size == null ? null : cb.equal(root.get("plant_size"), size);
    }

    public static Specification<Plant> hasCharacteristic(String characteristic) {
        return (root, query, cb) -> characteristic == null ? null : cb.equal(root.get("characteristic"), characteristic);
    }

    public static Specification<Plant> hasPriceLessThanEqual(Integer price) {
        return (root, query, cb) -> price == null ? null : cb.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Plant> hasNameContaining(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return null;
            return cb.like(cb.lower(root.get("plant_name")), "%" + keyword.toLowerCase() + "%");
        };
    }
}