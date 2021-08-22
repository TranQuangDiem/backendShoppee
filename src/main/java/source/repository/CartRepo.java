package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Cart;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findByUser_Id(long userID);
    List<Cart> findByUser_IdAndStatus_Id(long userId, long status);
    Cart findById(long id);
}
