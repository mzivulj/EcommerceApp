package com.shop.ecommerce.controller;

import com.shop.ecommerce.dto.ResponseDto;
import com.shop.ecommerce.dto.user.SignInDto;
import com.shop.ecommerce.dto.user.SignInReponseDto;
import com.shop.ecommerce.dto.user.SignupDto;
import com.shop.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@CrossOrigin("http://localhost:8081/")
public class UserController {

    @Autowired
    UserService userService;

    // two apis

    // signup

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }


    // signin@
    @CrossOrigin("http://localhost:8081/")
    @PostMapping("/signin")
    public SignInReponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }


}
