package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.RateProduct;
@Repository
public interface RateProductRepo extends JpaRepository<RateProduct,Long> {
}
