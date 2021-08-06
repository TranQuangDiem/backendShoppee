package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Rate;
@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {
}
