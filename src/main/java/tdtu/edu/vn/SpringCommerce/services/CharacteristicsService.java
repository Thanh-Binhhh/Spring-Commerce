package tdtu.edu.vn.SpringCommerce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.SpringCommerce.models.Characteristic;
import tdtu.edu.vn.SpringCommerce.repositories.CharacteristicRepository;

@Service
@RequiredArgsConstructor
public class CharacteristicsService {

    final CharacteristicRepository characteristicRepository;

    public Iterable<Characteristic> getCharacteristics() {
        return characteristicRepository.findAll();
    }
}