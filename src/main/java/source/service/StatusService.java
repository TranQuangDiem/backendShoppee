package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Status;
import source.repository.StatusRepo;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepo statusRepo;
    public List<Status> findAll(){
        return statusRepo.findAll();
    }
    public Status findById(long id){
        return statusRepo.findById(id);
    }
}
