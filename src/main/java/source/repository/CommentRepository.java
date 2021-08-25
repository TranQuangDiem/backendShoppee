package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByIdproductAndActiveOrderByIdDesc(long idproduct,int active);
    List<Comment> findByIdproductAndRateAndActiveOrderByIdDesc(long idproduct,int rate,int active);
    List<Comment> findByIdproductAndUser_Id(long idProduct, long userId);
    List<Comment> findByIdproductAndUser_IdAndColor_IdOrderByIdDesc(long idProduct, long userId,long idc);

}
