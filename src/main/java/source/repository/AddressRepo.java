package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Address;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
    List<Address> findByUser(long userId);
    Address findById(long id);
    Address findByStatus(boolean status);
    Address findByUserAndStatus(long id , boolean status);
    void deleteAllByUser(long userid);

}
