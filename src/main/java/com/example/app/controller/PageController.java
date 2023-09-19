package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    //list
    @GetMapping("/list")
    public String listPage() {
        return "bookList/list";
    }

    @GetMapping("/list/listDetail")
    public String listDetailPage() {
        return "bookList/listDetail";
    }

    @GetMapping("/list/review")
    public String reviewPage() {
        return "bookList/review";
    }
    
    //post
    @GetMapping("/post")
    public String postPage() {
        return "post/post";
    }
    @GetMapping("/post/postCheck")
    public String postCheckPage() {
        return "post/postCheck";
    }
    @GetMapping("/post/postDone")
    public String postDonePage() {
        return "post/postDone";
    }
    
    
    //mypage
    @GetMapping("/mypage")
    public String myPage() {
        return "mypage/mypage";
    }
    @GetMapping("/mypage/myShelf")
    public String myShelfPage() {
        return "mypage/myShelf";
    }
    @GetMapping("/mypage/myFavoriteBooks")
    public String myFavoriteBooksPage() {
        return "mypage/myFavoriteBooks";
    }
    

    //login
    @GetMapping("/login")
    public String loginPage() {
        return "login/login";
    }
    
    @GetMapping("/register")
    public String registerPage() {
        return "login/register";
    }
    
    @GetMapping("/register/registerCheck")
    public String registerCheckPage() {
        return "login/registerCheck";
    }
    
    @GetMapping("/register/registerDone")
    public String registerDonePage() {
        return "login/registerDone";
    }

}
