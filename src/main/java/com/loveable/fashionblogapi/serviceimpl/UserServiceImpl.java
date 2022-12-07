package com.loveable.fashionblogapi.serviceimpl;

import com.loveable.fashionblogapi.dto.UserResponseDto;
import com.loveable.fashionblogapi.dto.UserSignUpDto;
import com.loveable.fashionblogapi.entities.User;
import com.loveable.fashionblogapi.repositories.UserRepository;
import com.loveable.fashionblogapi.services.UserService;

import com.loveable.fashionblogapi.util.ApiResponse;
import com.loveable.fashionblogapi.util.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ResponseManager<UserResponseDto> responseManager;
    private final ResponseManager<String> logoutResponseManager;
    private final ResponseManager<List<UserResponseDto>> rm = new ResponseManager<>();
    private final ModelMapper mapper;

    @Override
    public ApiResponse<UserResponseDto> signUp(UserSignUpDto signUpDto) {
        if (signUpDto.getFirstName() == null)
            return responseManager.error("First Name is required", BAD_REQUEST);
        else if (signUpDto.getLastName() == null)
            return responseManager.error("Last Name is required", BAD_REQUEST);
        else if (signUpDto.getGender() == null)
            return responseManager.error("Gender is required", BAD_REQUEST);
        else if (signUpDto.getEmail() == null)
            return responseManager.error("Email is required", BAD_REQUEST);
        else if (signUpDto.getPassword() == null)
            return responseManager.error("Password is required", BAD_REQUEST);

        boolean userExist = userRepository.existsByEmail(signUpDto.getEmail());
        if (userExist)
            return responseManager.error("Email already exist", BAD_REQUEST);

        User user = mapper.map(signUpDto, User.class);
        userRepository.save(user);
        UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
        return responseManager.success(userResponseDto, CREATED);
    }

    @Override
    public ApiResponse<UserResponseDto> login(UserSignUpDto signUpDto, HttpSession httpSession) {
        Optional<User> user = userRepository.findUserByEmailAndPassword(signUpDto.getEmail(), signUpDto.getPassword());
        if (user.isPresent()) {
            UserResponseDto loggedInUser = mapper.map(user.get(), UserResponseDto.class);
            httpSession.setAttribute("currentUser", user.get());
            return responseManager.success(loggedInUser, FOUND);
        } else
            return responseManager.error("Incorrect credentials", CONFLICT);
    }

    @Override
    public ApiResponse<String> logout(HttpSession session) {
        session.invalidate();
        return logoutResponseManager.success("User logged out successfully", HttpStatus.OK);
    }

    @Override
    public ApiResponse<List<UserResponseDto>> getAllUsers(HttpSession httpSession) {
        User loggedInUser = (User) httpSession.getAttribute("currentUser");
        if (loggedInUser.getFirstName().equalsIgnoreCase("Mensa")) {
            //
            List<User> users = userRepository.findAll();
            List<UserResponseDto> responseDtoList = new ArrayList<>();
            if (users.isEmpty()) {
                return rm.error("No registered user", BAD_REQUEST);
            }
            users.forEach(user -> {
                UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
                responseDtoList.add(responseDto);
            });
            return rm.success(responseDtoList, FOUND);
        }
        return rm.error("Not Authorized", NON_AUTHORITATIVE_INFORMATION);
    }

}