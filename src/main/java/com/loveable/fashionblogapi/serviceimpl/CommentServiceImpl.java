package com.loveable.fashionblogapi.serviceimpl;

import com.loveable.fashionblogapi.dto.CommentCreationDto;
import com.loveable.fashionblogapi.dto.CommentResponseDto;
import com.loveable.fashionblogapi.entities.Comment;
import com.loveable.fashionblogapi.entities.Post;
import com.loveable.fashionblogapi.entities.User;
import com.loveable.fashionblogapi.repositories.CommentRepository;
import com.loveable.fashionblogapi.repositories.PostRepository;
import com.loveable.fashionblogapi.repositories.UserRepository;
import com.loveable.fashionblogapi.services.CommentService;
import com.loveable.fashionblogapi.util.ApiResponse;
import com.loveable.fashionblogapi.util.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ResponseManager responseManager;
    private final ModelMapper mapper;

    @Override
    public ApiResponse createComment(CommentCreationDto commentCreationDto, Long postId, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        if (currentUser == null) {
            Optional<User> anonymous = userRepository.findById(2L);
            currentUser = anonymous.get();
        }

        System.out.println(currentUser);

        Optional<Post> currentPost = postRepository.findById(postId);

        System.out.println(currentPost);

        if (currentPost.isEmpty())
            return responseManager.error("Post not Found or deleted", HttpStatus.BAD_REQUEST);

        System.out.println(commentCreationDto.getComment());

        if (commentCreationDto.getComment() == null)
            return responseManager.error("Comment can't be empty", HttpStatus.BAD_REQUEST);

        commentCreationDto.setUser(currentUser);
        commentCreationDto.setPost(currentPost.get());

        Comment comment = mapper.map(commentCreationDto, Comment.class);
        Comment savedComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = mapper.map(savedComment, CommentResponseDto.class);
        return responseManager.success(commentResponseDto, HttpStatus.OK);
    }
}
