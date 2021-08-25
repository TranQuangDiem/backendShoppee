package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.SearchHistory;
import source.repository.SearchHistoryRepo;

import java.util.List;

@Service
public class SearchServive {
    @Autowired
    SearchHistoryRepo searchHistoryRepo;
    public void save(SearchHistory searchHistory){
        searchHistoryRepo.save(searchHistory);
    }
    public List<SearchHistory> findAll(){
        return searchHistoryRepo.findAllByOrderByIdDesc();
    }
    public SearchHistory findByTitle(String title){
        return searchHistoryRepo.findByTitle(title);
    }
    public void delete (SearchHistory searchHistory){
        searchHistoryRepo.delete(searchHistory);
    }
    public SearchHistory findById(long id){
        return searchHistoryRepo.findById(id);
    }
}
