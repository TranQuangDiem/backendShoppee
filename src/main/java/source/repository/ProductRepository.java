package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByIdAndActive(long id, int active);

    List<Product> findByActive(int active);
    // danh sách sản phẩm theo  tên sản phẩm
    List<Product> findByNameLikeAndActive(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo tăng dần
    List<Product> findByNameLikeAndActiveOrderByCreateDateAsc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo giảm dần
    List<Product> findByNameLikeAndActiveOrderByCreateDateDesc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo giảm dần
    List<Product> findByNameLikeAndActiveOrderByCreateDateDescPriceDesc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo giảm dần
    List<Product> findByNameLikeAndActiveOrderByCreateDateDescPriceAsc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo số lượng bán ,  giá tăng dần
    List<Product> findByNameLikeAndActiveOrderByQuantitySoldDescPriceDesc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo số lượng bán.   giá giảm dần
    List<Product> findByNameLikeAndActiveOrderByQuantitySoldDescPriceAsc(String name,int active);
    List<Product> findByNameLikeAndActiveOrderByQuantitySoldDesc(String name, int active);

    List<Product> findByNameLikeAndActiveAndStatusOrderByPriceAsc(String name, int active, String status);
    List<Product> findByNameLikeAndActiveAndStatusOrderByPriceDesc(String name, int active, String status);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo giá tăng dần
    List<Product> findByNameLikeAndActiveOrderByPriceAsc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo giá giảm dần
    List<Product> findByNameLikeAndActiveOrderByPriceDesc(String name,int active);

    // danh sách sản phẩm theo  tên sản phẩm sắp xếp theo ngày tạo tăng dần
    List<Product> findByNameLikeAndActiveAndStatus(String name,int active,String status);

    //danh sách sản phẩm theo khoảng giá
    List<Product> findByPriceBetweenAndActive(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByPriceAsc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByPriceDesc(double min, double max,int active);

    //danh sách sản phẩm theo khoảng giá theo ngày tạo giảm dần
    List<Product> findByPriceBetweenAndActiveOrderByCreateDateDescPriceAsc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByCreateDateDescPriceDesc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByCreateDateDesc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByQuantitySoldDescPriceAsc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByQuantitySoldDescPriceDesc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveOrderByQuantitySoldDesc(double min, double max,int active);
    List<Product> findByPriceBetweenAndActiveAndStatusOrderByPriceAsc(double min, double max,int active,String status);
    List<Product> findByPriceBetweenAndActiveAndStatusOrderByPriceDesc(double min, double max,int active,String status);
    List<Product> findByPriceBetweenAndActiveAndStatus(double min, double max,int active,String status);

    //danh sách sản phẩm theo khoảng sao (đánh giá)
    List<Product> findByRateBetweenAndActive(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByPriceAsc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByPriceDesc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByCreateDateDesc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByCreateDateDescPriceAsc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByQuantitySoldDesc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveAndStatusOrderByPriceAsc(double rateMin, double rateMax, int active,String status);
    List<Product> findByRateBetweenAndActiveAndStatusOrderByPriceDesc(double rateMin, double rateMax, int active,String Status);
    List<Product> findByRateBetweenAndActiveAndStatus(double rateMin, double rateMax, int active,String Status);
    List<Product> findByRateBetweenAndActiveOrderByQuantitySoldDescPriceAsc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByQuantitySoldDescPriceDesc(double rateMin, double rateMax, int active);
    List<Product> findByRateBetweenAndActiveOrderByCreateDateDescPriceDesc(double rateMin, double rateMax, int active);

    //danh sách sản phẩm theo trạng thái
    List<Product> findByStatusAndActive(String status, int active);

    //danhsachs sản phẩm theo ngày tạo giảm dần
    List<Product> findByActiveOrderByCreateDateDesc(int active);

    // danh sách sản phẩm sắp xếp theo ngày tạo tăng dần
    List<Product> findByActiveOrderByCreateDateAsc(int active);

    //danh sách sản phẩm sắp xếp theo giá giảm dần
    List<Product> findByActiveOrderByPriceAsc(int active);

    //danh sách sản phẩm sắp xếp theo giá tăng dần
    List<Product> findByActiveOrderByPriceDesc(int active);

    // danh sách sản phẩm theo brand
    List<Product> findByBrand_IdAndActive(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderByPriceAsc(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderByPriceDesc(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderByCreateDateDescPriceAsc(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderByCreateDateDescPriceDesc(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderByQuantitySoldDescPriceAsc(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderByQuantitySoldDescPriceDesc(long id,int active);
    List<Product> findByBrand_IdAndActiveAndStatusOrderByPriceDesc(long id,int active,String status);
    List<Product> findByBrand_IdAndActiveAndStatusOrderByPriceAsc(long id,int active,String status);
    List<Product> findByBrand_IdAndActiveOrderByCreateDateDesc(long id,int active);
    List<Product> findByBrand_IdAndActiveOrderByQuantitySoldDesc(long id,int active);
    List<Product> findByBrand_IdAndActiveAndStatus(long id,int active,String status);
}
