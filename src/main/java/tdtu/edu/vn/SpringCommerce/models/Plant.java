package tdtu.edu.vn.SpringCommerce.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "plants")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, length = 30)
    String image;

    @Column(nullable = false, length = 50)
    String plant_name;

    @Column(length = 500)
    String description;

    @Column(nullable = false)
    int price;

    String category;
    String plant_size;
    String characteristic;
}