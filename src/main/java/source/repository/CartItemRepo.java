package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.CartItem;

import java.util.List;
@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {

    List<CartItem> findByUserAndActive(long userId,int active);
}
