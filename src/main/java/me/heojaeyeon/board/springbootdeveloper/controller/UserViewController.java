package me.heojaeyeon.board.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//html 뷰로 이동하는 컨트롤러
@Controller
public class UserViewController {
    @GetMapping("/login")
    public String login(){
        return "oauthLogin";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
}
