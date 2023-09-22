package com.example.app.domain;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Books {
	
	private Integer id;
	
	@NotBlank(message = "タイトルを入力してください")
	@Size(max = 30, message = "半角英数字30文字以内で入力してください")
	private String bookName;
	
	@NotBlank(message = "著者を入力してください")
	@Size(max = 30, message = "30文字以内で入力してください")
	private String author;  // 修正：フィールド名を "author" に変更
	
	@NotBlank(message = "点数を入力してください")
	@Range(min=0, max=100, message="0点以上100以下で入力して下さい" )
	private String score;
	
	@NotBlank(message = "キャッチコピーを入力してください")
	private String catchCopy;
	
	private String pointFirst;
	private String pointSecond;
	private String pointThird;
	
	@NotBlank(message = "感想を入力してください")
	@Size(max = 150, message = "150文字以内で入力してください")
	private String review;
	
	private String buyLink;
	
	private String img;

}
