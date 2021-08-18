package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import source.entity.Address;
import source.jwt.JwtTokenProvider;
import source.payload.Message;
import source.service.AddressService;


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
            return ResponseEntity.ok().body(addressService.findByUserAndStatus(userId,true));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).body(new Message("error"));
        }
    }
    @PostMapping("/address")
    public ResponseEntity saveAddress(@RequestBody Address address,@RequestHeader("Authorization") String jwt){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            address.setUser(userId);
            addressService.save(address,userId);
            return ResponseEntity.ok().body(addressService.finByUser(userId));
        }catch (Exception e){
            e.printStackTrace();
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
            address1.setStatus(address.isStatus());
            addressService.save(address1,userId);
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
            address1.setStatus(address.isStatus());
            addressService.save(address1,userId);
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
            return ResponseEntity.ok().body(addressService.finByUser(userId));
        }catch (Exception e){
            return ResponseEntity.status(401).body(new Message("error"));
        }
    }
}
