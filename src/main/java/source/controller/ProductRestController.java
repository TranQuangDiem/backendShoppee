package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import source.entity.Image;
import source.entity.Product;
import source.service.ProductService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    ProductService productService;
    @GetMapping("/product")
    public String addProduct(){
        Product p = new Product();
        p.setName("abc");
        ArrayList<Image> images = new ArrayList<Image>();
        Image m = new Image();
        m.setTitle("dfghjk");
        m.setPath("fghj");
        images.add(m);
        p.setImages(images);
        productService.save(p);
        return "thanhcong";
    }
}
