package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Member;

@Mapper
public interface MemberMapper {
	Member selectByLoginId(String userId) throws Exception;
	void addMember(Member member);
}
