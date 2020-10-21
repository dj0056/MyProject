package com.icia.testtwo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.testtwo.dto.CommentDTO;
import com.icia.testtwo.service.CommentService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="commentwrite")
	public @ResponseBody List<CommentDTO> commentWrite(@ModelAttribute CommentDTO comment){
		List<CommentDTO> commentList = commentService.commentWrite(comment);
		return commentList;
	}
	
}
