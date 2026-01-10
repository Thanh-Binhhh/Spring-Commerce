package tdtu.edu.vn.SpringCommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;
import tdtu.edu.vn.SpringCommerce.models.Plant;
import tdtu.edu.vn.SpringCommerce.repositories.PlantsRepository;
import tdtu.edu.vn.SpringCommerce.services.PlantsService;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PlantsServiceTest {
    @Mock
    private PlantsRepository plantsRepository;

    @InjectMocks
    private PlantsService plantsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPlants() {
        List<Plant> plantList = Arrays.asList(new Plant(), new Plant());
        Page<Plant> page = new PageImpl<>(plantList);
        when(plantsRepository.findAll(PageRequest.of(0, 12))).thenReturn(page);

        Page<Plant> result = plantsService.getPlants(0);

        assertThat(result.getContent()).hasSize(2);
        verify(plantsRepository).findAll(PageRequest.of(0, 12));
    }

    @Test
    void testGetPlantById() {
        Plant plant = new Plant();
        when(plantsRepository.findById(1)).thenReturn(Optional.of(plant));

        Optional<Plant> result = plantsService.getPlant(1);

        assertThat(result).isPresent();
        verify(plantsRepository).findById(1);
    }

    @Test
    void testGetPlantsByIds() {
        List<Integer> ids = List.of(1, 2, 3);
        List<Plant> plants = Arrays.asList(new Plant(), new Plant(), new Plant());
        when(plantsRepository.findAllById(ids)).thenReturn(plants);

        List<Plant> result = plantsService.getPlantsByIds(ids);

        assertThat(result).hasSize(3);
        verify(plantsRepository).findAllById(ids);
    }

    @Test
    void testGetPlantsPrice() {
        Plant min = new Plant();
        min.setPrice(10);
        Plant max = new Plant();
        max.setPrice(100);

        when(plantsRepository.findTopByOrderByPriceAsc()).thenReturn(min);
        when(plantsRepository.findTopByOrderByPriceDesc()).thenReturn(max);

        int[] result = plantsService.getPlantsPrice();

        assertThat(result[0]).isEqualTo(10);
        assertThat(result[1]).isEqualTo(100);
    }

    @Test
    void testFilterPlants() {
        List<Plant> filtered = List.of(new Plant(), new Plant());
        when(plantsRepository.findAll((Example<Plant>) any())).thenReturn(filtered);

        List<Plant> result = plantsService.filterPlants("rose", "flower", "small", "fragrant", 100);

        assertThat(result).hasSize(2);
        verify(plantsRepository).findAll((Example<Plant>) any());
    }
}
