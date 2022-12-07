package com.loveable.fashionblogapi.serviceimpl;

import com.loveable.fashionblogapi.dto.PostLikeResponseDto;
import com.loveable.fashionblogapi.entities.Post;
import com.loveable.fashionblogapi.entities.PostLike;
import com.loveable.fashionblogapi.entities.User;
import com.loveable.fashionblogapi.repositories.PostLikeRepository;
import com.loveable.fashionblogapi.repositories.PostRepository;
import com.loveable.fashionblogapi.services.PostLikeService;
import com.loveable.fashionblogapi.util.ApiResponse;
import com.loveable.fashionblogapi.util.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeServiceImpl implements PostLikeService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final ResponseManager<PostLikeResponseDto> responseManager;
    private final ModelMapper mapper;
    @Override
    public ApiResponse<PostLikeResponseDto> likePost(Long postId, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        if (currentUser == null)
            currentUser = new User("Anonymous", "Anonymous", "Anonymous", "Anonymous", "Anonymous");
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null)
            return responseManager.error("Post not valid or deleted", HttpStatus.BAD_REQUEST);
        PostLike postLike = new PostLike();
        postLike.setPost(post);
        postLike.setUser(currentUser);
        postLikeRepository.save(postLike);
        PostLikeResponseDto postLikeResponseDto = mapper.map(postLike, PostLikeResponseDto.class);
        return responseManager.success(postLikeResponseDto, HttpStatus.OK);
    }
}