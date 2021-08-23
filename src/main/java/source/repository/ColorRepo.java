package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Color;

@Repository
public interface ColorRepo extends JpaRepository<Color,Long> {
    public Color findById(long id);
}
