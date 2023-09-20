package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Member {
	
	private Integer id;
	
	@NotBlank(message = "IDを入力してください")
	@Size(max = 30, message = "半角英数字30文字以内で入力してください")
	private String userId;
	
	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 8,max = 30, message = "半角英数字8文字以上30文字以内で入力してください")
	private String password;
	
	@NotBlank(message = "ユーザー名を入力してください")
	@Size(max = 30, message = "半角英数字30文字以内で入力してください")
	private String name;
	
	private Integer gender;
	
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;

}
