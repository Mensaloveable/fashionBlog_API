package com.loveable.fashionblogapi.serviceimpl;

import com.loveable.fashionblogapi.dto.PostCreationDto;
import com.loveable.fashionblogapi.dto.PostResponseDto;
import com.loveable.fashionblogapi.entities.Post;
import com.loveable.fashionblogapi.entities.User;
import com.loveable.fashionblogapi.repositories.PostRepository;
import com.loveable.fashionblogapi.services.PostService;
import com.loveable.fashionblogapi.util.ApiResponse;
import com.loveable.fashionblogapi.util.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.*;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final ModelMapper mapper;
    private final ResponseManager responseManager;
    private final PostRepository postRepository;

    @Override
    public ApiResponse<PostResponseDto> createPost(PostCreationDto postCreationDto, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        if (currentUser.getFirstName().equalsIgnoreCase("Mensa")) {
            if (postCreationDto.getPost() == null)
                return responseManager.error("Post body cannot be empty", BAD_REQUEST);
            if (postCreationDto.getCategory() == null)
                return responseManager.error("Post category cannot be empty", BAD_REQUEST);
            postCreationDto.setUser(currentUser);

            Post post = mapper.map(postCreationDto, Post.class);
            Post savedPost = postRepository.save(post);

            PostResponseDto postResponseDto = mapper.map(savedPost, PostResponseDto.class);
            return responseManager.success(postResponseDto, OK);
        }
        return responseManager.error("Not Authorized", NOT_ACCEPTABLE);
    }
}
