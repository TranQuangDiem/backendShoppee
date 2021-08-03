package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Comment;
import source.payload.CommentDTO;
import source.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ModelMapper mapper;
    public List<CommentDTO> findAll(){
        List<Comment> comments =commentRepository.findAll();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        if (comments!=null){
            for (Comment comment: comments) {
                CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
                commentDTOList.add(commentDTO);
            }
            return commentDTOList;
        }
        return commentDTOList;
    }
    public List<CommentDTO> findByIdProduct(long idproduct){
        List<Comment> comments =commentRepository.findByIdproductOrderByDateDesc(idproduct);
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        if (comments!=null){
            for (Comment comment: comments) {
                CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
                commentDTOList.add(commentDTO);
            }
            return commentDTOList;
        }
        return commentDTOList;
    }
}
