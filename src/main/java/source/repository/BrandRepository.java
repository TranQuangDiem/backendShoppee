package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
}
