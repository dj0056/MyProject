package com.icia.testtwo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.testtwo.dto.CommentDTO;

@Repository
public class CommentDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	public int commentWrite(CommentDTO comment) {
		return sql.insert("Comment.commentWrite", comment);
	}

	public List<CommentDTO> commentList(int cbnumber) {
		return sql.selectList("Comment.commentList", cbnumber);
	}
	
}
