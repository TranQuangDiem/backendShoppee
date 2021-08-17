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
    public void save(Address address,long userId){
        if (address.isStatus()){
            Address a = addressRepo.findByUserAndStatus(userId,true);
            if(a!=null) {
                a.setStatus(false);
                addressRepo.save(a);
            }
        }
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
    public Address findByUserAndStatus(long userId,boolean status){
        return addressRepo.findByUserAndStatus(userId,status);
    }
}
