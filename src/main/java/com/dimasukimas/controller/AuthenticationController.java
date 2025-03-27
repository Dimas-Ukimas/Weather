package com.dimasukimas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-in")
public class AuthenticationController {

    private final static String SIGN_IN_PAGE = "sign-in";

    @GetMapping
    public String signIn(){
        return SIGN_IN_PAGE;
    }

}
