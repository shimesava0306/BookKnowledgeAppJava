package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper mapper;

	@Override
	public boolean isCorrectIdAndPassword(String userId, String password) throws Exception {
		
	Member member = mapper.selectByLoginId(userId);
		
	// ログインID が正しいかチェック
	// ⇒ ログインID が正しくなければ、管理者データは取得されない
	if(member == null) {
	System.out.println("NG_ID");
	return false;
	}
	
	// パスワードが正しいかチェック
	if (!BCrypt.checkpw(password, member.getPassword())) {
    System.out.println("NG_PASS");
    return false;
	}
	
	return true;
	}

}
