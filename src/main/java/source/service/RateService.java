package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Rate;
import source.repository.RateRepository;

import java.util.List;

@Service
public class RateService {
    @Autowired
    RateRepository rateRepository;
    public List<Rate> findAll(){
        return rateRepository.findAll();
    }
}
