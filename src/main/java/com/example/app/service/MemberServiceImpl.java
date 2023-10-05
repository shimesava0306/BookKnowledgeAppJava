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
	if(member == null) {
	return false;
	}
	
	// パスワードが正しいかチェック
	if (!BCrypt.checkpw(password, member.getPassword())) {
  return false;
	}
	
	return true;
	}

}
