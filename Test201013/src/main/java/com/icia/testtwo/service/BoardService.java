package com.icia.testtwo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.testtwo.dao.BoardDAO;
import com.icia.testtwo.dto.BoardDTO;
import com.icia.testtwo.dto.CommentDTO;
import com.icia.testtwo.dto.PageDTO;

@Service
public class BoardService {

	
	@Autowired
	private BoardDAO boardDAO;
	
	private ModelAndView mav;
	
	private static final int PAGE_LIMIT = 3;
	private static final int BLOCK_LIMIT = 5;

	public ModelAndView boardWrite(BoardDTO board) {
		mav = new ModelAndView();
		int writeResult = boardDAO.boardWrite(board);
		if(writeResult > 0) {
			mav.setViewName("redirect:/boardlistpaging");
		} else {
			mav.setViewName("boardt/BoardWriteFail");
		}
		return mav;
	}

	public ModelAndView boardList() {
		mav = new ModelAndView();
		List<BoardDTO> bList = boardDAO.boardList();
		mav.addObject("boardList", bList);
		mav.setViewName("boardt/BoardList");
		return mav;
	}

	public ModelAndView boardView(int bnumber, int page) {
		mav = new ModelAndView();
		BoardDTO bDTO = new BoardDTO();
		boardDAO.boardHits(bnumber);
		bDTO = boardDAO.boardView(bnumber);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		int cbnumber = bnumber;
		commentList = boardDAO.commentList(cbnumber);
		mav.addObject("commentList", commentList);
		mav.addObject("page", page);
		mav.addObject("boardView", bDTO);
		mav.setViewName("boardt/BoardView");
		return mav;
	}

	public ModelAndView boardUpdate(int bnumber) {
		mav = new ModelAndView();
		BoardDTO bDTO = new BoardDTO();
		bDTO = boardDAO.boardView(bnumber);
		mav.addObject("boardUpdate", bDTO);
		mav.setViewName("boardt/BoardUpdate");
		return mav;
	}

	public ModelAndView boardUpdateProcess(BoardDTO board) {
		mav = new ModelAndView();
		int updateResult = boardDAO.boardUpdateProcess(board);
		if(updateResult > 0) {
			mav.setViewName("redirect:/boardview?bnumber="+board.getBnumber());
		} else {
			mav.setViewName("boardt/BoardUpdateFail");
		}
		return mav;
	}

	public ModelAndView boardDelete(int bnumber) {
		mav = new ModelAndView();
		int deleteResult = boardDAO.boardDelete(bnumber);
		if(deleteResult > 0) {
			mav.setViewName("redirect:/boardlistpaging");
		} else {
			mav.setViewName("boardt/BoardDeleteFail");
		}
		return mav;
	}

	public ModelAndView boardWriteFile(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		
		String savePath = "D:\\source\\spring\\Test201013\\src\\main\\webapp\\resources\\uploadfile\\"+bfilename;
		
		if(!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		int writeResult = boardDAO.boardWriteFile(board);
		if(writeResult > 0) {
			mav.setViewName("redirect:/boardlistpaging");
		} else {
			mav.setViewName("boardt/BoardWriteFileFail");
		}
		return mav;
	}
	
	public ModelAndView boardWriteFileT(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		String savePath ="D:\\source\\spring\\Test201013\\src\\main\\webapp\\resources\\uploadfile\\"+bfilename;
		
		if(!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		
		board.setBfilename(bfilename);
		
		int writeFileResult = boardDAO.boardWriteFile(board);
		if(writeFileResult > 0) {
			mav.setViewName("redirect:/boardlistpaging");
		} else {
			mav.setViewName("boardt/BoardWriteFileFail");
		}
		return mav;
	}
	
	public ModelAndView boardListPaging(int page) {
		mav = new ModelAndView();
		int listCount = boardDAO.listCount();
		// listCount 글갯수
		int startRow = (page-1)*PAGE_LIMIT + 1;
		int endRow = page*PAGE_LIMIT;
		
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = boardDAO.boardListPaging(paging);
		
		int maxPage = (int)(Math.ceil((double)listCount/PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page/BLOCK_LIMIT)))- 1) * BLOCK_LIMIT + 1;
		
		int endPage = startPage + BLOCK_LIMIT - 1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("membert/MemberMain");
		
		return mav;
	}

	public ModelAndView boardSearch(String searchtype, String keyword) {
		mav = new ModelAndView();
		List<BoardDTO> searchList = boardDAO.boardSearch(searchtype, keyword);
		mav.addObject("boardList", searchList);
		mav.setViewName("membert/MemberMain");
		return mav;
	}

	public ModelAndView boardHit() {
		mav = new ModelAndView();
		List<BoardDTO> boardHit = boardDAO.boardHit();
		mav.addObject("boardList", boardHit);
		mav.setViewName("membert/MemberMain");
		return mav;
	}
}
