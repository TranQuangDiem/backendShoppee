package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import source.entity.Address;
import source.jwt.JwtTokenProvider;
import source.payload.AddressDTO;
import source.payload.Message;
import source.service.AddressService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    AddressService addressService;

    @GetMapping("/address")
    public ResponseEntity getAddress ( @RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
//            AddressDTO addressDTO = new AddressDTO();
//            addressDTO.setAddressList();
            return ResponseEntity.ok().body(addressService.finByUser(userId));
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("error"));
        }
    }
    @GetMapping("/address/status")
    public ResponseEntity getAddressByStatus ( @RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            AddressDTO addressDTO = new AddressDTO();
            List<Address> addressList = new ArrayList<>();
//            addressList.add(addressService.findByStatus("default"));
//            addressDTO.setAddressList(addressList);
            return ResponseEntity.ok().body(addressService.findByStatus("default"));
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("error"));
        }
    }
    @PostMapping("/address")
    public ResponseEntity saveAddress(@RequestBody Address address,@RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            address.setStatus("none");
            address.setUser(userId);
            addressService.save(address);
//            AddressDTO addressDTO = new AddressDTO();
//            addressDTO.setAddressList(addressService.finByUser(userId));
            return ResponseEntity.ok().body(addressService.finByUser(userId));
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("error"));
        }
    }
    @PutMapping("/address/{id}")
    public ResponseEntity saveAddress(@RequestBody Address address,@PathVariable("id") long id,@RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            Address address1 = addressService.findById(id);
            address1.setAddress(address.getAddress());
            address1.setName(address.getName());
            address1.setPhone(address.getPhone());
            addressService.save(address1);
//            AddressDTO addressDTO = new AddressDTO();
//            addressDTO.setAddressList(addressService.finByUser(userId));
            return ResponseEntity.ok().body(addressService.finByUser(userId));
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("error"));
        }
    }
    @PutMapping("/address/status/{id}")
    public ResponseEntity setStatus ( @PathVariable("id") long id,@RequestBody Address address,@RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            Address address1 = addressService.findById(id);
            address1.setStatus(address.getStatus());
            addressService.save(address1);
//            AddressDTO addressDTO = new AddressDTO();
//            addressDTO.setAddressList(addressService.finByUser(userId));
            return ResponseEntity.ok().body(addressService.finByUser(userId));
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("error"));
        }
    }
    @DeleteMapping("/address/{id}")
    public ResponseEntity getAddress ( @PathVariable("id") long id,@RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            addressService.delete(addressService.findById(id));
//            AddressDTO addressDTO = new AddressDTO();
//            addressDTO.setAddressList(addressService.finByUser(userId));
            return ResponseEntity.ok().body(addressService.finByUser(userId));
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("error"));
        }
    }
}
