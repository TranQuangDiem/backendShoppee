package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source.entity.Brand;
import source.entity.Product;
import source.payload.Message;
import source.payload.Pagination;
import source.payload.ProductsReponse;
import source.service.BrandService;
import source.service.ProductService;
import source.service.RateProductService;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;
    @Autowired
    RateProductService rateProductService;
    @GetMapping("/brands")
    public ResponseEntity getBrands(){
        List<Brand> brands = brandService.findAll();
            return ResponseEntity.ok().body(brands);
    }

    @GetMapping("/product")
    public ResponseEntity getProductById(@RequestParam(value = "id") long id){
        Product product = productService.findById(id);
        if(product!=null)
        return ResponseEntity.ok().body(productService.findById(id));
        return ResponseEntity.badRequest().body(new Message("product không có trong hệ thống"));
    }
    @GetMapping("/rates")
    public ResponseEntity getRate(){
        return ResponseEntity.ok().body(rateProductService.findAll());
    }

    @GetMapping("/products")
    public ResponseEntity danhsach(@RequestParam(value = "search", required = false) String search, @RequestParam(required = false, value = "_page") Integer page,
                                   @RequestParam("_limit") Optional<Integer> size, @RequestParam(value = "brand", required = false) Optional<Long> brand,
                                   @RequestParam(value = "_sortBy",required = false) String sortBy, @RequestParam(value = "priceMin",required = false) Optional<Double> priceMin,
                                   @RequestParam(value = "priceMax", required = false) Optional<Double> priceMax,@RequestParam(value = "_order",required = false) String sortPrice,
                                   @RequestParam(value = "rate",required = false) Optional<Double> rate) {
        double price_min = priceMin.orElse((double) 0);
        double price_max = priceMax.orElse((double) 0);
        double rate1 = rate.orElse((double) 0);
        long brand_id = brand.orElse((long) -1);
        double rateMin =-1;
        double rateMax =0;
        if (rate1==0){
            rateMin =-1;
            rateMax =0;
        }else if (rate1<=2) {
            rateMin=0;
           rateMax = 5;
        }else if (rate1<3){
            rateMin=2;
            rateMax=5;
        }else if (rate1<4){
            rateMin=3;
            rateMax=5;
        }else if (rate1<5){
            rateMin=4;
            rateMax=5;
        }else if (rate1==5){
            rateMin=4.99;
            rateMax=5;
        }
        if("asc".equals(sortBy)){
            sortPrice="asc";
            sortBy="price";
        }else if("desc".equals(sortBy)){
            sortPrice="desc";
            sortBy="price";
        }
        List<Product> productPage;
        productPage = productService.findPaginated(search,price_min,price_max,brand_id,sortBy,sortPrice,rateMin,rateMax);
        int pagesize = size.orElse(12);
        PagedListHolder<Product> pagedListHolder = new PagedListHolder<>(productPage);
        pagedListHolder.setPageSize(pagesize);
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
        }
        ProductsReponse products = new ProductsReponse();
        products.setProducts(pagedListHolder.getPageList());
        Pagination pagination = new Pagination();
        pagination.set_limit(pagesize);
        pagination.set_page(page);
        pagination.set_total(productPage.size());
        products.setPagination(pagination);
        return ResponseEntity.ok().body(products);
    }

}
