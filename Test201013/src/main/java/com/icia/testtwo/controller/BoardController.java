package com.icia.testtwo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.testtwo.dto.BoardDTO;
import com.icia.testtwo.service.BoardService;

@Controller
public class BoardController {
	
	private ModelAndView mav;
	
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="boardwriteform")
	public String boardWriteForm() {
		return "boardt/BoardWrite";
	}
	
	@RequestMapping(value="boardwrite")
	public ModelAndView boardWrite(@ModelAttribute BoardDTO board) {
		mav = boardService.boardWrite(board);
		return mav;
	}
	
	@RequestMapping(value="boardlist")
	public ModelAndView boardList() {
		mav = boardService.boardList();
		return mav;
	}
	
	@RequestMapping(value="boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber,
			@RequestParam(value="page",required=false,defaultValue="1") int page) {
		mav = boardService.boardView(bnumber, page);
		return mav;
	}
	
	@RequestMapping(value="boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber) {
		mav = boardService.boardUpdate(bnumber);
		return mav;
	}
	
	@RequestMapping(value="boardupdateprocess")
	public ModelAndView boardUpdateProcess(@ModelAttribute BoardDTO board) {
		mav = boardService.boardUpdateProcess(board);
		return mav;
	}
	
	@RequestMapping(value="boarddelete")
	public ModelAndView boardDelete(@RequestParam("bnumber") int bnumber) {
		mav = boardService.boardDelete(bnumber);
		return mav;
	}
	
	@RequestMapping(value="boardwritefileform")
	public String boardWriteFileForm() {
		return "boardt/BoardWriteFile";
	}
	
	@RequestMapping(value="/boardwritefile")
	public ModelAndView boardWriteFile(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		mav = boardService.boardWriteFileT(board);
		return mav;
	}
	
	@RequestMapping(value="/boardlistpaging")
	public ModelAndView boardListPaging(
			@RequestParam(value="page",required=false,defaultValue="1") int page) {
		// required 필수인지 아닌지, defaultValue 기본값 1로 잡음
		mav = boardService.boardListPaging(page);
		return mav;
	}
	
	@RequestMapping(value="/boardsearch")
	public ModelAndView boardSearch(@RequestParam("searchtype") String searchtype,
			@RequestParam("keyword") String keyword) {
		mav = boardService.boardSearch(searchtype, keyword);
		return mav;
	}
	
	@RequestMapping(value="/boardhit")
	public ModelAndView boardHit() {
		mav = boardService.boardHit();
		return mav;
	}
	
	
	
}
