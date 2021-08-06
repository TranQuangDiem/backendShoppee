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
            if (sortBy!=null) {
                if ("ctime".equals(sortBy)) {
                    return productRepository.findByNameLikeAndActiveOrderByCreateDateDesc(search, 1);
                } else if ("pop".equals(sortBy)) {
                    return productRepository.findByNameLikeAndActiveAndStatus(search, 1, "phổ biến");
                } else if ("sales".equals(sortBy)) {
                    return productRepository.findByNameLikeAndActiveOrderByQuantitySoldDesc(search, 1);
                } else if ("price".equals(sortBy) && sortPrice != null) {
                    if ("asc".equals(sortPrice)) {
                        return productRepository.findByNameLikeAndActiveOrderBySalePriceAsc(search, 1);
                    } else {
                        return productRepository.findByNameLikeAndActiveOrderBySalePriceDesc(search, 1);
                    }
                }
            }else {
                return productRepository.findByNameLikeAndActive(search, 1);
            }
        }else if (brand!=0){
            if (sortBy!=null){
                if ("ctime".equals(sortBy)){
                    return productRepository.findByBrand_IdAndActiveOrderByCreateDateDesc(brand,1);
                }else if ("sales".equals(sortBy)){
                    return productRepository.findByBrand_IdAndActiveOrderBySalePriceAsc(brand,1);
                }else if ("pop".equals(sortBy)){
                    return productRepository.findByBrand_IdAndActiveAndStatus(brand,1,"phổ biến");
                }else if ("price".equals(sortBy)&&sortPrice!=null) {
                    if ("asc".equals(sortPrice)) {
                        return productRepository.findByBrand_IdAndActiveOrderBySalePriceAsc(brand, 1);
                    } else {
                        return productRepository.findByBrand_IdAndActiveOrderBySalePriceDesc(brand, 1);
                    }
                }
            }else {
                return productRepository.findByBrand_IdAndActive(brand,1);
            }
        }else if (priceMin!=0&&priceMax!=0){
               if (sortBy!=null){
                    if ("ctime".equals(sortBy)){
                        return productRepository.findBySalePriceBetweenAndActiveOrderByCreateDateDesc(priceMin,priceMax,1);
                    }else if ("sales".equals(sortBy)){
                        return productRepository.findBySalePriceBetweenAndActiveOrderByQuantitySoldDesc(priceMin,priceMax,1);
                    }else if ("pop".equals(sortBy)){
                        return productRepository.findBySalePriceBetweenAndActiveAndStatus(priceMin,priceMax,1,"phổ biến");
                    }else if (sortPrice!=null){
                        if ("asc".equals(sortPrice)){
                            return productRepository.findBySalePriceBetweenAndActiveOrderBySalePriceAsc(priceMin,priceMax,1);
                        }else{
                            return productRepository.findBySalePriceBetweenAndActiveOrderBySalePriceDesc(priceMin,priceMax,1);
                        }
                    }
                }else {
                    return productRepository.findBySalePriceBetweenAndActive(priceMin,priceMax,1);
                }
        }else if (rateMin!=-1&&rateMax!=0) {
                    if (sortBy != null) {
                        if ("ctime".equals(sortBy)) {
                            return productRepository.findByRateBetweenAndActiveOrderByCreateDateDesc(rateMin, rateMax, 1);
                        } else if ("sales".equals(sortBy)) {
                            return productRepository.findByRateBetweenAndActiveOrderByQuantitySoldDesc(rateMin, rateMax, 1);
                        } else if ("pop".equals(sortBy)) {
                            return productRepository.findByRateBetweenAndActiveAndStatus(rateMin, rateMax, 1, "phổ biến");
                        } else if ("price".equals(sortBy)&&sortPrice != null) {
                            if ("asc".equals(sortPrice)) {
                                return productRepository.findByRateBetweenAndActiveOrderBySalePriceAsc(rateMin, rateMax, 1);
                            } else {
                                return productRepository.findByRateBetweenAndActiveOrderBySalePriceDesc(rateMin, rateMax, 1);
                            }
                        }
                    } else {
                        return productRepository.findByRateBetweenAndActive(rateMin, rateMax, 1);
                    }
        }else if (sortBy != null) {
            if ("ctime".equals(sortBy)) {
                return productRepository.findByActiveOrderByCreateDateDesc(1);
            } else if ("sales".equals(sortBy)) {
                return productRepository.findByActiveOrderByQuantitySoldDesc(1);
            } else if ("pop".equals(sortBy)) {
                return productRepository.findByActiveAndStatus(1, "phổ biến");
            } else if ("price".equals(sortBy)&&sortPrice != null) {
                if ("asc".equals(sortPrice)) {
                    return productRepository.findByActiveOrderBySalePriceAsc(1);
                } else {
                    return productRepository.findByActiveOrderBySalePriceDesc(1);
                }
            }
        }
        return products;
    }
    public List<Product> findAll(){
        return productRepository.findByActiveOrderByCreateDateAsc(1);
    }
}
