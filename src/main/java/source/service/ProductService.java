package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Product;
import source.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    // hiển thị sản phẩm theo id
    public Product findById(long id){
        return productRepository.findByIdAndActive(id,1);
    }

    public void save(Product product){
        productRepository.save(product);
    }
    public List<Product> findPaginated(String search, double priceMin,double priceMax, long brand,
                                       String sortBy, String sortPrice, double rateMin,double rateMax) {
        List<Product> products =productRepository.findByActive(1);
        if (search!=null) {
            if (sortBy!=null&&sortPrice!=null){
                //mới nhất
                if ("ctime".equals(sortBy)&& "asc".equals(sortPrice)){
                    return productRepository.findByNameLikeAndActiveOrderByPriceAsc(search,1);
                }else if ("ctime".equals(sortBy)&& "desc".equals(sortPrice)){
                    return productRepository.findByNameLikeAndActiveOrderByPriceDesc(search,1);
                }
                //bán chạy
                else if("sales".equals(sortBy)&&"asc".equals(sortPrice)){
                    return productRepository.findByNameLikeAndActiveOrderByPriceAsc(search,1);
                }else if ("sales".equals(sortBy)&& "desc".equals(sortPrice)){
                    return productRepository.findByNameLikeAndActiveOrderByPriceDesc(search,1);
                }
                //Phổ biến
                else if("pop".equals(sortBy)&&"asc".equals(sortPrice)){
                    return productRepository.findByNameLikeAndActiveAndStatusOrderByPriceAsc(search,1,"phổ biến");
                }else if ("pop".equals(sortBy)&& "desc".equals(sortPrice)){
                    return productRepository.findByNameLikeAndActiveAndStatusOrderByPriceDesc(search,1,"phổ biến");
                }
            }else if (sortBy!=null){
                if ("ctime".equals(sortBy)){
                    return productRepository.findByNameLikeAndActiveOrderByCreateDateDesc(search,1);
                }else if ("pop".equals(sortBy)){
                    return productRepository.findByNameLikeAndActiveAndStatus(search,1,"phổ biến");
                }else if ("sales".equals(sortBy)){
                    return productRepository.findByNameLikeAndActiveOrderByQuantitySoldDesc(search,1);
                }

            }else if (sortPrice!=null){
                if ("asc".equals(sortPrice)){
                    return productRepository.findByNameLikeAndActiveOrderByPriceAsc(search,1);
                }else{
                    return productRepository.findByNameLikeAndActiveOrderByPriceDesc(search,1);
                }
            }else {
                return productRepository.findByNameLikeAndActive(search, 1);
            }
        }else if (brand!=0){
            if (sortBy!=null&&sortPrice!=null){
                //mới nhất
                if ("ctime".equals(sortBy)&& "asc".equals(sortPrice)){
                    return productRepository.findByBrand_IdAndActiveOrderByPriceAsc(brand,1);
                }else if ("ctime".equals(sortBy)&& "desc".equals(sortPrice)){
                    return productRepository.findByBrand_IdAndActiveOrderByPriceDesc(brand,1);
                }
                //bán chạy
                else if("sales".equals(sortBy)&&"asc".equals(sortPrice)){
                    return productRepository.findByBrand_IdAndActiveOrderByPriceAsc(brand,1);
                }else if ("sales".equals(sortBy)&& "desc".equals(sortPrice)){
                    return productRepository.findByBrand_IdAndActiveOrderByPriceDesc(brand,1);
                }
                //Phổ biến
                else if("pop".equals(sortBy)&&"asc".equals(sortPrice)){
                    return productRepository.findByBrand_IdAndActiveAndStatusOrderByPriceAsc(brand,1,"phổ biến");
                }else if ("pop".equals(sortBy)&& "desc".equals(sortPrice)){
                    return productRepository.findByBrand_IdAndActiveAndStatusOrderByPriceDesc(brand,1,"phổ biến");
                }
            }else if (sortBy!=null){
                if ("ctime".equals(sortBy)){
                    return productRepository.findByBrand_IdAndActiveOrderByCreateDateDesc(brand,1);
                }else if ("sales".equals(sortBy)){
                    return productRepository.findByBrand_IdAndActiveOrderByPriceAsc(brand,1);
                }else if ("pop".equals(sortBy)){
                    return productRepository.findByBrand_IdAndActiveAndStatus(brand,1,"phổ biến");
                }
            }else if (sortPrice!=null){
                if ("asc".equals(sortPrice)){
                    return productRepository.findByBrand_IdAndActiveOrderByPriceAsc(brand,1);
                }else{
                    return productRepository.findByBrand_IdAndActiveOrderByPriceDesc(brand,1);
                }
            }else {
                return productRepository.findByBrand_IdAndActive(brand,1);
            }
        }else if (priceMin!=0&&priceMax!=0){
                if (sortBy!=null&&sortPrice!=null){
                    //mới nhất
                    if ("ctime".equals(sortBy)&& "asc".equals(sortPrice)){
                        return productRepository.findByPriceBetweenAndActiveOrderByPriceAsc(priceMin,priceMax,1);
                    }else if ("ctime".equals(sortBy)&& "desc".equals(sortPrice)){
                        return productRepository.findByPriceBetweenAndActiveOrderByPriceDesc(priceMin,priceMax,1);
                    }
                    //bán chạy
                    else if("sales".equals(sortBy)&&"asc".equals(sortPrice)){
                        return productRepository.findByPriceBetweenAndActiveOrderByPriceAsc(priceMin,priceMax,1);
                    }else if ("sales".equals(sortBy)&& "desc".equals(sortPrice)){
                        return productRepository.findByPriceBetweenAndActiveOrderByPriceDesc(priceMin,priceMax,1);
                    }
                    //Phổ biến
                    else if("pop".equals(sortBy)&&"asc".equals(sortPrice)){
                        return productRepository.findByPriceBetweenAndActiveAndStatusOrderByPriceAsc(priceMin,priceMax,1,"phổ biến");
                    }else if ("pop".equals(sortBy)&& "desc".equals(sortPrice)){
                        return productRepository.findByPriceBetweenAndActiveAndStatusOrderByPriceDesc(priceMin,priceMax,1,"phổ biến");
                    }
                }else if (sortBy!=null){
                    if ("ctime".equals(sortBy)){
                        return productRepository.findByPriceBetweenAndActiveOrderByCreateDateDesc(priceMin,priceMax,1);
                    }else if ("sales".equals(sortBy)){
                        return productRepository.findByPriceBetweenAndActiveOrderByQuantitySoldDesc(priceMin,priceMax,1);
                    }else if ("pop".equals(sortBy)){
                        return productRepository.findByPriceBetweenAndActiveAndStatus(priceMin,priceMax,1,"phổ biến");
                    }

                }else if (sortPrice!=null){
                    if ("asc".equals(sortPrice)){
                        return productRepository.findByPriceBetweenAndActiveOrderByPriceAsc(priceMin,priceMax,1);
                    }else{
                        return productRepository.findByPriceBetweenAndActiveOrderByPriceDesc(priceMin,priceMax,1);
                    }
                }else {
                    return productRepository.findByPriceBetweenAndActive(priceMin,priceMax,1);
                }
        }else if (rateMin!=-1&&rateMax!=0) {
            if (sortBy != null && sortPrice != null) {
                //mới nhất
                if ("ctime".equals(sortBy) && "asc".equals(sortPrice)) {
                    return productRepository.findByRateBetweenAndActiveOrderByCreateDateDescPriceAsc(rateMin, rateMax, 1);
                } else if ("ctime".equals(sortBy) && "desc".equals(sortPrice)) {
                    return productRepository.findByRateBetweenAndActiveOrderByCreateDateDescPriceDesc(rateMin, rateMax, 1);
                }
                //bán chạy
                else if ("sales".equals(sortBy) && "asc".equals(sortPrice)) {
                    return productRepository.findByRateBetweenAndActiveOrderByQuantitySoldDescPriceAsc(rateMin, rateMax, 1);
                } else if ("sales".equals(sortBy) && "desc".equals(sortPrice)) {
                    return productRepository.findByRateBetweenAndActiveOrderByQuantitySoldDescPriceDesc(rateMin, rateMax, 1);
                }
                //Phổ biến
                else if ("pop".equals(sortBy) && "asc".equals(sortPrice)) {
                    return productRepository.findByRateBetweenAndActiveAndStatusOrderByPriceAsc(rateMin, rateMax, 1, "phổ biến");
                } else if ("pop".equals(sortBy) && "desc".equals(sortPrice)) {
                    return productRepository.findByRateBetweenAndActiveAndStatusOrderByPriceDesc(rateMin, rateMax, 1, "phổ biến");
                }
            } else if (sortBy != null) {
                if ("ctime".equals(sortBy)) {
                    return productRepository.findByRateBetweenAndActiveOrderByCreateDateDesc(rateMin, rateMax, 1);
                } else if ("sales".equals(sortBy)) {
                    return productRepository.findByRateBetweenAndActiveOrderByQuantitySoldDesc(rateMin, rateMax, 1);
                } else if ("pop".equals(sortBy)) {
                    return productRepository.findByRateBetweenAndActiveAndStatus(rateMin, rateMax, 1, "phổ biến");
                }

            } else if (sortPrice != null) {
                if ("asc".equals(sortPrice)) {
                    return productRepository.findByRateBetweenAndActiveOrderByPriceAsc(rateMin, rateMax, 1);
                } else {
                    return productRepository.findByRateBetweenAndActiveOrderByPriceDesc(priceMin, priceMax, 1);
                }
            } else {
                return productRepository.findByRateBetweenAndActive(rateMin, rateMax, 1);
            }
        }
        return products;
    }
    public List<Product> findAll(){
        return productRepository.findByActiveOrderByCreateDateAsc(1);
    }
}
