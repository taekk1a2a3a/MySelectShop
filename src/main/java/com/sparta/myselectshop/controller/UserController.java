package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.LoginRequestDto;
import com.sparta.myselectshop.dto.SignupRequestDto;
import com.sparta.myselectshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/signup")
    public String signup(SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "redirect:/api/user/login";
    }

    @ResponseBody //ajax 에서 body 쪽에 값이 넘어오기 때문에 @RequestBody 를 써줘야 한다.
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // HttpServletResponse 는 HttpRequest 에서 Header 가 넘어와서 받아오는 것 처럼 Client 쪽으로 반환을 할 때에는 response 객체를 반환한다.
        // 반환 할 response Header 에 만들어준 토큰을 넣어주기 위해 받아온다.
        userService.login(loginRequestDto, response);
        return "success";
    }

}