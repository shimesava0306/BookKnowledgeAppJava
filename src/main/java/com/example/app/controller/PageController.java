package com.example.app.controller;

import java.io.File;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.Books;
import com.example.app.domain.Member;
import com.example.app.mapper.BooksMapper;
import com.example.app.mapper.MemberMapper;
import com.example.app.service.BooksService;
import com.example.app.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
	
	private final BooksMapper mapperBooks;
	private final MemberMapper mapper;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
    
  	private final BooksService service;
  	
    //list
    @GetMapping("/list")
    public String list(Model model) throws Exception {
    	model.addAttribute("books", service.getBooksList());
    	return "bookList/list";
    }

    @GetMapping("/list/listDetail/{id}")
    public String listDetailPage(@PathVariable Integer id, Model model)throws Exception {
    		model.addAttribute("book", service.getBooksById(id));
        return "bookList/listDetail";
    }


    @GetMapping("/list/review")
    public String reviewPage() {
        return "bookList/review";
    }
    
    //post
    @GetMapping("/post")
    public String postPage(@ModelAttribute("books") Books books) {
        return "post/post";
    }
    
    @PostMapping("/post")
    public String add(@Valid Books books, Errors errors,@RequestParam MultipartFile file) throws Exception {
    	
    	if(!file.isEmpty()) {
    	String fileName = file.getOriginalFilename();
    	File dest = new File("C:/Users/zd2N05/pleiades/workspace/BookKnowledgeApp/src/main/resources/static/img/uploads/" + fileName);
    	file.transferTo(dest);
    	books.setImg("/uploads/" + fileName);
    	}
     
    	
        if (errors.hasErrors()) {
            return "post/post"; 
        }
        mapperBooks.addBooks(books);
        return "redirect:/post/postDone";
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
    

    private final MemberService memberService;
    
    //login
    @GetMapping("/login")
    public String loginPage(@ModelAttribute("member") Member member) {
    return "login/login";
    }
    
    @PostMapping("/login")
    public String login(
    		@Valid Member member,
    		Errors errors,
    		HttpSession session) throws Exception {
    	
    	
    		// パスワードが正しくない
    		if(!memberService.isCorrectIdAndPassword(member.getUserId(),member.getPassword())) {
    		errors.rejectValue("userId", "error.incorrect_id_password");
    		System.out.println("NG_PASS");
    		return "login/login";
    		}
    		// 正しいログインID とパスワード
    		// ⇒ セッションにログインID を格納し、リダイレクト
    		session.setAttribute("userId", member.getUserId());
    		return "redirect:/";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    // セッションを破棄し、トップページへ遷移
    session.invalidate();
    return "redirect:/";
    }
    
    
    @GetMapping("/register")
    public String registerPage(@ModelAttribute("member") Member member) {
        return "login/register";
    }
    
    @PostMapping("/register")
    public String add(@Valid Member member, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            return "login/register"; // エラー時に登録ページに戻す
        }
        
        String hashed = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());
        member.setPassword(hashed);
        
        mapper.addMember(member);
        return "redirect:/register/registerDone";
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