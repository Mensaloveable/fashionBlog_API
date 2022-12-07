package com.loveable.fashionblogapi;

import com.loveable.fashionblogapi.entities.User;
import com.loveable.fashionblogapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@RequiredArgsConstructor
@SpringBootApplication
public class FashionBlogApiApplication {

    private final UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(FashionBlogApiApplication.class, args);
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            User Admin = new User("Mensa", "Loveable", "MALE", "mensa@merge.com", "1234");
            User anonymous = new User("Anonymous", "Anonymous", "Anonymous", "Anonymous", "Anonymous");

            userRepository.saveAll(Arrays.asList(Admin, anonymous));
        };
    }
}
