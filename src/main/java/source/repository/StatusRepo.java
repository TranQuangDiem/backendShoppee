package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status,Long> {
    Status findById(long id);
}
