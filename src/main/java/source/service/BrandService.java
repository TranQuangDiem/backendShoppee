package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Brand;
import source.repository.BrandRepository;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;
   public List<Brand> findAll(){
        return brandRepository.findAll();
    }
}
