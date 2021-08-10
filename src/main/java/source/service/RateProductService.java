package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.RateProduct;
import source.repository.RateProductRepo;

import java.util.List;

@Service
public class RateProductService {
    @Autowired
    RateProductRepo rateProductRepo;
    public List<RateProduct> findAll(){
        return rateProductRepo.findAll();
    }
}
