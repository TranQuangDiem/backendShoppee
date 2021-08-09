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
    public List<CommentDTO> findAllIdProduct(long idProduct){
        List<Comment> comments =commentRepository.findByIdproductAndActiveOrderByIdDesc(idProduct,1);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        if (comments!=null){
            for (Comment comment: comments) {
                CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
                commentDTO.setUserName(comment.getUser().getFullname());
                commentDTOList.add(commentDTO);
            }
            return commentDTOList;
        }
        return commentDTOList;
    }
    public List<CommentDTO> findByIdProductAndRate(long idproduct,int rate){
        List<Comment> comments =commentRepository.findByIdproductAndActiveOrderByIdDesc(idproduct,1);
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        if (rate!=0){
            comments = commentRepository.findByIdproductAndRateAndActiveOrderByIdDesc(idproduct,rate,1);
        }
        if (comments!=null){
            for (Comment comment: comments) {
                CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
                commentDTO.setUserName(comment.getUser().getFullname());
                commentDTOList.add(commentDTO);
            }
            return commentDTOList;
        }
        return commentDTOList;
    }
}
