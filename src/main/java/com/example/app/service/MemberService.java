package com.example.app.service;

public interface MemberService {
	boolean isCorrectIdAndPassword(String userId, String password)throws Exception;
}