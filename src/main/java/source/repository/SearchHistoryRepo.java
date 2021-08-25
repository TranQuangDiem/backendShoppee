package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.SearchHistory;

import java.util.List;

@Repository
public interface SearchHistoryRepo extends JpaRepository<SearchHistory,Long> {
     SearchHistory findByTitle(String title);
     List<SearchHistory> findAllByOrderByIdDesc();
     SearchHistory findById(long id);
}
