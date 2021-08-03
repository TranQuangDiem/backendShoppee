package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import source.entity.Comment;
import source.payload.CommentDTO;
import source.payload.CommentPaginationDTO;
import source.payload.Pagination;
import source.service.CommentService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;
    @GetMapping("/comments")
    public ResponseEntity getComments(@RequestParam("id") long id, @RequestParam(value = "_page",required = false)Integer page,@RequestParam(value = "_limit",required = false) Optional<Integer> size){

        List<CommentDTO> comments;
        comments = commentService.findByIdProduct(id);
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
}
