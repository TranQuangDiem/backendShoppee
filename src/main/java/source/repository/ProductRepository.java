package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import source.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByIdAndActive(long id, int active);

    List<Product> findByActive(int active);
    List<Product> findByActiveOrderByCreateDateDesc(int active);
    List<Product> findByActiveOrderByQuantitySoldDesc(int active);
    List<Product> findByActiveAndStatus(int active,String status);
    // danh sách sản phẩm theo  tên sản phẩm
    @Query(value = "select * from Product p where p.name LIKE %:name% and p.active=:active",nativeQuery = true)
    List<Product> findByNameIsLikeAndActive(@Param("name") String name,@Param("active") int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo tăng dần
    List<Product> findByNameLikeAndActiveOrderByCreateDateAsc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo giảm dần
    @Query(value = "select * from Product p where p.name LIKE %:name% and p.active=:active order by create_date desc",nativeQuery = true)
    List<Product> findByNameIsLikeAndActiveOrderByCreateDateDesc(@Param("name") String name,@Param("active") int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo giảm dần
    List<Product> findByNameLikeAndActiveOrderByCreateDateDescPriceDesc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo giảm dần
    List<Product> findByNameLikeAndActiveOrderByCreateDateDescPriceAsc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo số lượng bán ,  giá tăng dần
    List<Product> findByNameLikeAndActiveOrderByQuantitySoldDescPriceDesc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo số lượng bán.   giá giảm dần
    List<Product> findByNameLikeAndActiveOrderByQuantitySoldDescPriceAsc(String name,int active);
    @Query(value = "select * from Product p where p.name LIKE %:name% and p.active=:active order by quantity_sold desc",nativeQuery = true)
    List<Product> findByNameIsLikeAndActiveOrderByQuantitySoldDesc(@Param("name") String name,@Param("active") int active);

    List<Product> findByNameLikeAndActiveAndStatusOrderByPriceAsc(String name, int active, String status);
    List<Product> findByNameLikeAndActiveAndStatusOrderByPriceDesc(String name, int active, String status);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo giá tăng dần
    @Query(value = "select * from Product p where p.name LIKE %:name% and p.active=:active order by sale_price asc ",nativeQuery = true)
    List<Product> findByNameIsLikeAndActiveOrderBySalePriceAsc(@Param("name") String name,@Param("active") int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo giá giảm dần
    @Query(value = "select * from Product p where p.name LIKE %:name% and p.active=:active order by sale_price desc",nativeQuery = true)
    List<Product> findByNameIsLikeAndActiveOrderBySalePriceDesc(@Param("name") String name,@Param("active") int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo tăng dần
    @Query(value = "select * from Product p where p.name LIKE %:name% and p.active=:active and p.status=:status",nativeQuery = true)
    List<Product> findByNameIsLikeAndActiveAndStatus(@Param("name") String name,@Param("active") int active,@Param("status") String status);

    //danh sách sản phẩm theo khoảng giá
    List<Product> findBySalePriceBetweenAndActive(double min, double max,int active);
    List<Product> findBySalePriceBetweenAndActiveOrderBySalePriceAsc(double min, double max,int active);
    List<Product> findBySalePriceBetweenAndActiveOrderBySalePriceDesc(double min, double max,int active);

    //danh sách sản phẩm theo khoảng giá theo ngày tạo giảm dần
    List<Product> findByPriceBetweenAndActiveOrderByCreateDateDescPriceAsc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByCreateDateDescPriceDesc(double min, double max,int active);
    List<Product> findBySalePriceBetweenAndActiveOrderByCreateDateDesc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByQuantitySoldDescPriceAsc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByQuantitySoldDescPriceDesc(double min, double max,int active);
    List<Product> findBySalePriceBetweenAndActiveOrderByQuantitySoldDesc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveAndStatusOrderByPriceAsc(double min, double max,int active,String status);
    List<Product> findByPriceBetweenAndActiveAndStatusOrderByPriceDesc(double min, double max,int active,String status);
    List<Product> findBySalePriceBetweenAndActiveAndStatus(double min, double max,int active,String status);

    //danh sách sản phẩm theo khoảng sao (đánh giá)
    List<Product> findByRateBetweenAndActiveOrderByRateDesc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderBySalePriceAsc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderBySalePriceDesc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByCreateDateDesc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByCreateDateDescPriceAsc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByQuantitySoldDesc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveAndStatusOrderByPriceAsc(double rateMin, double rateMax, int active,String status);
    List<Product> findByRateBetweenAndActiveAndStatusOrderByPriceDesc(double rateMin, double rateMax, int active,String Status);
    List<Product> findByRateBetweenAndActiveAndStatus(double rateMin, double rateMax, int active,String Status);
    //danh sách sản phẩm theo trạng thái
    List<Product> findByStatusAndActive(String status, int active);

    // danh sách sản phẩm sắp xếp theo ngày tạo tăng dần
    List<Product> findByActiveOrderByCreateDateAsc(int active);

    //danh sách sản phẩm sắp xếp theo giá giảm dần
    List<Product> findByActiveOrderBySalePriceAsc(int active);

    //danh sách sản phẩm sắp xếp theo giá tăng dần
    List<Product> findByActiveOrderBySalePriceDesc(int active);

    // danh sách sản phẩm theo brand
    List<Product> findByBrand_IdAndActive(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderBySalePriceAsc(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderBySalePriceDesc(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderByCreateDateDesc(long id,int active);
    List<Product> findByBrand_IdAndActiveAndStatus(long id,int active,String status);
}
