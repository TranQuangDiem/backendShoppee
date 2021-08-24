package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source.entity.Rate;
import source.jwt.JwtTokenProvider;
import source.payload.*;
import source.service.CommentService;
import source.service.RateService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    RateService rateService;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @GetMapping("/comments")
    public ResponseEntity getComments(@RequestParam("id") long id, @RequestParam(value = "_page",required = false) Integer page,@RequestParam(value = "_limit",required = false) Optional<Integer> size,
                                      @RequestParam(value = "rate",required = false) Optional<Integer> rate){

        List<CommentDTO> comments;
        int rate1= rate.orElse(-1);
        comments = commentService.findByIdProductAndRate(id,rate1-1);
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
        List<Rate> rates = rateService.findAll();
        List<CommentDTO> comments = commentService.findAllIdProduct(idProduct);
        rates.get(0).setSumCmt(comments.size());
        rates.get(1).setSumCmt(commentService.findByIdProductAndRate(idProduct,1).size());
        rates.get(2).setSumCmt(commentService.findByIdProductAndRate(idProduct,2).size());
        rates.get(3).setSumCmt(commentService.findByIdProductAndRate(idProduct,3).size());
        rates.get(4).setSumCmt(commentService.findByIdProductAndRate(idProduct,4).size());
        rates.get(5).setSumCmt(commentService.findByIdProductAndRate(idProduct,5).size());

//        double avg = 0;
//        double total =0;
//        for (CommentDTO commentDTO:comments) {
//            total+=commentDTO.getRate();
//        }
//        avg = (total*5)/(comments.size()*5);
//        rate.setAvg(avg);
        return ResponseEntity.ok().body(rates);
    }
    @PostMapping("/orderManager/comment")
    public ResponseEntity saveComment(@RequestHeader("Authorization") String jwt, @RequestBody List<CommentRequest> commentRequests){
        try {
            String[] a = jwt.split(" ");
            long userId = tokenProvider.getUserIdFromJWT(a[1]);
            for (CommentRequest comment : commentRequests) {
                commentService.save(comment, userId);
            }
            return ResponseEntity.ok().body(new Message("lưu thành công"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Message("error"));
        }
    }
}
