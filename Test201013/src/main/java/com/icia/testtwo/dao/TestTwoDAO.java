package com.icia.testtwo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.testtwo.dto.TestTwoDTO;

@Repository
public class TestTwoDAO {
	
	@Autowired
	SqlSessionTemplate sql;
	
	public int memberJoin(TestTwoDTO testtwo) {
		if(testtwo.getKakaoId() != null) {
			return sql.insert("TestTwo.kakaoMemberJoin", testtwo);			
		}
		else if(testtwo.getNaverId() != null) {
			return sql.insert("TestTwo.naverMemberJoin", testtwo);			
		}
		else {
			return sql.insert("TestTwo.memberJoin", testtwo);			
		}
	}

	public String memberLogin(TestTwoDTO testtwo) {
		return sql.selectOne("TestTwo.memberLogin", testtwo);
	}

	public int memberUpdateProcess(TestTwoDTO testtwo) {
		return sql.update("TestTwo.memberUpdate", testtwo);
	}

	public List<TestTwoDTO> memberList() {
		return sql.selectList("TestTwo.memberList");
	}

	public TestTwoDTO memberView(String tid) {
		return sql.selectOne("TestTwo.memberView", tid);
	}

	public int memberDelete(String tid) {
		return sql.delete("TestTwo.memberDelete", tid);
	}

	public String kakaoLogin(String kakaoId) {
		return sql.selectOne("TestTwo.kakaoLogin", kakaoId);
	}

	public String naverLogin(String naverId) {
		return sql.selectOne("TestTwo.naverLogin", naverId);
	}

	public String idOverlap(String tid) {
		return sql.selectOne("TestTwo.idOverlap", tid);
	}
	
	
	
	
	
	
	
	
	
	
}
