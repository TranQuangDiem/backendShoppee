package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Address;
import source.repository.AddressRepo;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepo addressRepo;
    public void save(Address address){
        addressRepo.save(address);
    }
    public Address findById(long id){
        return addressRepo.findById(id);
    }
    public List<Address> finByUser(long userId){
        return addressRepo.findByUser(userId);
    }
    public void delete(Address address){
        addressRepo.delete(address);
    }
    public Address findByStatus(String status){
        return addressRepo.findByStatus(status);
    }
}
