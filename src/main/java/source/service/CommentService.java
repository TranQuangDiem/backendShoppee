package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Comment;
import source.payload.CommentDTO;
import source.payload.CommentRequest;
import source.repository.ColorRepo;
import source.repository.CommentRepository;
import source.repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    ColorRepo colorRepo;
    @Autowired
    UserRepository userRepository;
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
        if (rate>0){
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
    public void save (CommentRequest commentRequest,long userId){
        Comment comment = new Comment();
        comment.setColor(colorRepo.findById(commentRequest.getIdc()));
        comment.setUser(userRepository.findById(userId));
        comment.setContent(commentRequest.getContent());
        comment.setRate(commentRequest.getRate());
        comment.setIdproduct(commentRequest.getIdp());
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        Timestamp datetime = new Timestamp(date.getTime());
        comment.setDate(datetime);
        comment.setActive(1);
        commentRepository.save(comment);
    }
}
