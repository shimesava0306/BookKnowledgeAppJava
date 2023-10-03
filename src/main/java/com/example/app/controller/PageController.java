package com.example.app.controller;

import java.io.File;
import java.util.List;

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
import com.example.app.mapper.MemberMapper;
import com.example.app.service.BooksService;
import com.example.app.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {

	private final MemberMapper mapper;
	private final BooksService service;

	@GetMapping("/")
	public String indexPage(Model model) throws Exception {
		//DBの中からランダムで情報を取得したものをモデルに格納
		model.addAttribute("book", service.randomById());
		return "index";
	}

	//list
	@GetMapping("/list")
	public String list(Model model) throws Exception {
		//全登録書籍を取得しモデルに格納
		model.addAttribute("books", service.getBooksList());
		return "bookList/list";
	}

	@GetMapping("/list/listDetail/{id}")
	public String listDetailPage(@PathVariable Integer id, Model model) throws Exception {
		//クリックされた書籍のIDを取得し、その書籍の詳細情報をモデルに格納
		model.addAttribute("book", service.getBooksById(id));
		return "bookList/listDetail";
	}

	@GetMapping("/list/delete/{id}")
	public String detailDeletePage(@PathVariable Integer id, Model model) throws Exception {
		//選択された書籍の情報を削除
		service.getBookDeleteById(id);
		return "bookList/delete";
	}

	@GetMapping("/list/edit/{id}")
	//選択された書籍の情報を修正
	public String detailEditPage(@PathVariable Integer id, @ModelAttribute("books") Books books) throws Exception {
		return "bookList/edit";
	}

	@PostMapping("/list/edit/{id}")
	public String update(@Valid Books books, Errors errors, Model model, @RequestParam MultipartFile file)
			throws Exception {
		//画像ファイルを画像パス化
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			//画像の保存先を選択
			File dest = new File(
			"/home/trainee/uploads/" + fileName);
			//画像の保存
			file.transferTo(dest);
			//DBに保存する画像パスを設定、格納
			books.setImg("/uploads/" + fileName);
		}
		//バリデーションエラーの場合、再度修正ページにリダイレクト
		if (errors.hasErrors()) {
			return "bookList/edit";
		}
		// 本を更新する
		service.updateBooks(books);
		// 本の一覧画面にリダイレクト
		return "redirect:/list";
	}
	
	@GetMapping("/list/review")
	public String reviewPage() {
		return "bookList/review";
	}
	
	@GetMapping("/list/search")
	//キーワードで入力された値をrequestparamで取得
	public String searchPage(@RequestParam(name = "keyword") String keyword, Model model) throws Exception {
		//入力されたキーワードにて検索、抽出
	    List<Books> searchList = service.searchBooks(keyword);
	    //返ってきた値をモデルに格納
	    model.addAttribute("search", searchList);
	    //検索結果ページに出力
	    return "bookList/search";
	}

	//post
	@GetMapping("/post")
	public String postPage(@ModelAttribute("books") Books books) {
		return "post/post";
	}

	@PostMapping("/post")
	public String add(@Valid Books books, Errors errors, HttpSession session, @RequestParam MultipartFile file)
			throws Exception {
			//画像ファイルを画像パス化
			if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			//画像の保存先を選択
			File dest = new File(
					"/home/trainee/uploads/" + fileName);
			//画像の保存
			file.transferTo(dest);
			//DBに保存する画像パスを設定、格納
			books.setImg("/uploads/" + fileName);
			}
			//バリデーションエラーの場合、再度作成ページにリダイレクト
			if (errors.hasErrors()) {
			return "post/post";
			}
			//ユーザーIDをString型の変数に挿入
			String userId = (String) session.getAttribute("userId");
			//データベースに書籍情報、ユーザーIDを保存
			service.addBooks(books, userId);

			return "redirect:/post/postDone";
			}

	/*
	 * 未実装
	 * 訓練終了後に着手予定
	 */
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
	public String myShelfPage(Model model, HttpSession session) {
		//セッションのユーザーIDをフィールドに格納
		String userId = (String) session.getAttribute("userId");
		//自分が登録した書籍のみ取得
		List<Books> userBooks = service.selectByUserId(userId);
		//上記の取得した書籍をモデルに格納
		model.addAttribute("userBooks", userBooks);
		return "mypage/myShelf";
	}

	/*
	 * 未実装
	 * 訓練終了後に着手予定
	 */
	@GetMapping("/mypage/myFavoriteBooks")
	public String myFavoriteBooksPage() {
		return "mypage/myFavoriteBooks";
	}

	//login
	private final MemberService memberService;

	@GetMapping("/login")
	public String loginPage(@ModelAttribute("member") Member member) {
		return "login/login";
	}

	@PostMapping("/login")
	public String login(
			@Valid Member member,
			Errors errors,
			HttpSession session) throws Exception {
		// 入力に不備がある
		if (errors.hasErrors()) {
			return "login/login";
		}
		// パスワードが正しくない
		if (!memberService.isCorrectIdAndPassword(member.getUserId(), member.getPassword())) {
			errors.rejectValue("userId", "error.incorrect_id_password");
			System.out.println("NG_PASS");
			return "login/login";
		}
		// 正しいログインID とパスワード
		// ⇒ セッションにログインID を格納し、リダイレクト
		session.setAttribute("userId", member.getUserId());

		return "redirect:/list";

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
	public String add(@Valid Member member, Errors errors, HttpSession session) throws Exception {
		// エラー時に登録ページに戻す
		if (errors.hasErrors()) {
			return "login/register";
		}
		//PWをハッシュ化
		String hashed = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());
		//ハッシュ化したPWを格納
		member.setPassword(hashed);
		//DBに会員情報を登録
		mapper.addMember(member);
		//セッションにユーザーIDを保存
		session.setAttribute("userId", member.getUserId());

		return "redirect:/register/registerDone";
	}

	/*
	 * 未実装
	 * 訓練終了後に着手予定
	 */
	@GetMapping("/register/registerCheck")
	public String registerCheckPage() {
		return "login/registerCheck";
	}

	@GetMapping("/register/registerDone")
	public String registerDonePage() {
		return "login/registerDone";
	}

}