package tdtu.edu.vn.SpringCommerce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.SpringCommerce.models.Size;
import tdtu.edu.vn.SpringCommerce.repositories.SizeRepository;

@Service
@RequiredArgsConstructor
public class SizeService {

    final SizeRepository sizeRepository;

    public Iterable<Size> getSizes() {
        return sizeRepository.findAll();
    }
}
