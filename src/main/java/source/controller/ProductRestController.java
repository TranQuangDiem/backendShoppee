package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source.entity.Product;
import source.payload.Message;
import source.payload.ProductsReponse;
import source.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    ProductService productService;
    @GetMapping("/product/{id}")
    public ResponseEntity getProductById(@PathVariable(value = "id") long id){
        Product product = productService.findById(id);
        if(product!=null)
        return ResponseEntity.ok().body(productService.findById(id));
        return ResponseEntity.badRequest().body(new Message("product không có trong hệ thống"));
    }
    @GetMapping("/products")
    public ResponseEntity danhsach(@RequestParam(value = "search", required = false) String search, @RequestParam(required = false, value = "_page") Integer page,
                                   @RequestParam("_limit") Optional<Integer> size, @RequestParam(value = "brand", required = false) Optional<Long> brand,
                                   @RequestParam(value = "_sortBy",required = false) String sortBy, @RequestParam(value = "priceMin",required = false) Optional<Double> priceMin,
                                   @RequestParam(value = "priceMax", required = false) Optional<Double> priceMax,@RequestParam(value = "_sortPrice",required = false) String sortPrice,
                                   @RequestParam(value = "rate",required = false) Optional<Double> rate) {
        double price_min = priceMin.orElse((double) 0);
        double price_max = priceMax.orElse((double) 0);
        double rate1 = rate.orElse((double) 0);
        long brand_id = brand.orElse((long) 0);
        double rateMin =-1;
        double rateMax =0;
        if (rate1<=1) {
            rateMin=0;
           rateMax = 1.99;
        }else if (rate1<=2){
            rateMin=2;
            rateMax=2.99;
        }else if (rate1<=3){
            rateMin=3;
            rateMax=3.99;
        }else if (rate1<=4){
            rateMin=4;
            rateMax=4.99;
        }else{
            rateMin=4.9;
            rateMax=5;
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
        products.setLimit(pagesize);
        products.setPage(page);
        products.setTotal(productPage.size());
        return ResponseEntity.ok().body(products);
    }
}
