package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source.payload.CommentDTO;
import source.payload.CommentPaginationDTO;
import source.payload.Pagination;
import source.payload.RateComment;
import source.service.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;
    @GetMapping("/comments")
    public ResponseEntity getComments(@RequestParam("id") long id, @RequestParam(value = "_page",required = false) Integer page,@RequestParam(value = "_limit",required = false) Optional<Integer> size,
                                      @RequestParam(value = "rate",required = false) Optional<Integer> rate){

        List<CommentDTO> comments;
        int rate1= rate.orElse(0);
        comments = commentService.findByIdProductAndRate(id,rate1);
        int pagesize = size.orElse(12);
        PagedListHolder<CommentDTO> pagedListHolder = new PagedListHolder<>(comments);
        pagedListHolder.setPageSize(pagesize);
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
        }
        Pagination p = new Pagination();
        CommentPaginationDTO coments = new CommentPaginationDTO();
        coments.setComments(pagedListHolder.getPageList());
        p.set_page(page);
        p.set_total(comments.size());
        p.set_limit(pagesize);
        coments.setPagination(p);
        return ResponseEntity.ok().body(coments);
    }
    @GetMapping("/comment/rate")
    public ResponseEntity getRate(@RequestParam("id") long idProduct){

        List<CommentDTO> comments = commentService.findAllIdProduct(idProduct);
        RateComment rate = new RateComment();
        rate.setSumCmt(comments.size());
        rate.setId(1);
        rate.setStatus("active");
        rate.setValue("tất cả");

        RateComment rate1 = new RateComment();
        rate1.setSumCmt(commentService.findByIdProductAndRate(idProduct,1).size());
        rate1.setId(2);
        rate1.setStatus("none");
        rate1.setValue("1 sao");
        RateComment rate2 = new RateComment();
        rate2.setSumCmt(commentService.findByIdProductAndRate(idProduct,2).size());
        rate2.setId(3);
        rate2.setStatus("none");
        rate2.setValue("2 sao");
        RateComment rate3 = new RateComment();
        rate3.setSumCmt(commentService.findByIdProductAndRate(idProduct,3).size());
        rate3.setId(4);
        rate3.setStatus("none");
        rate3.setValue("3 sao");
        RateComment rate4 = new RateComment();
        rate4.setSumCmt(commentService.findByIdProductAndRate(idProduct,4).size());
        rate4.setId(5);
        rate4.setStatus("none");
        rate4.setValue("4 sao");
        RateComment rate5 = new RateComment();
        rate5.setSumCmt(commentService.findByIdProductAndRate(idProduct,5).size());
        rate5.setId(6);
        rate5.setStatus("none");
        rate5.setValue("5 sao");
        List<RateComment> list = new ArrayList<>();
        list.add(rate);
        list.add(rate1);
        list.add(rate2);
        list.add(rate3);
        list.add(rate4);
        list.add(rate5);
//        double avg = 0;
//        double total =0;
//        for (CommentDTO commentDTO:comments) {
//            total+=commentDTO.getRate();
//        }
//        avg = (total*5)/(comments.size()*5);
//        rate.setAvg(avg);
        return ResponseEntity.ok().body(list);
    }
}
