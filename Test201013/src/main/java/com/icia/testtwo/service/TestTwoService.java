package com.icia.testtwo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.testtwo.dao.TestTwoDAO;
import com.icia.testtwo.dto.TestTwoDTO;

@Service
public class TestTwoService {
	
	@Autowired
	private TestTwoDAO testtwoDAO;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	public ModelAndView memberJoin(TestTwoDTO testtwo) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile tfile = testtwo.getTfile();
		String tprofile = tfile.getOriginalFilename();
		
		String savePath = "D:\\source\\spring\\Test201013\\src\\main\\webapp\\resources\\profile\\"+tprofile;
		
		if(!tfile.isEmpty()) {
			tfile.transferTo(new File(savePath));
		}
		
		testtwo.setTprofile(tprofile);
		
		int joinResult = testtwoDAO.memberJoin(testtwo);
		if(joinResult > 0) {
			mav.setViewName("membert/MemberLogin");
		} else {
			mav.setViewName("membert/MemberJoinFail");
		}
		return mav;
	}

	public ModelAndView memberLogin(TestTwoDTO testtwo) {
		mav = new ModelAndView();
		String loginId = testtwoDAO.memberLogin(testtwo);
		
		if(loginId != null) {
			session.setAttribute("loginId", loginId);
			mav.setViewName("redirect:/boardlistpaging");
		}
		return mav;
	}

	public ModelAndView memberUpdateProcess(TestTwoDTO testtwo) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile tfile = testtwo.getTfile();
		String tprofile = tfile.getOriginalFilename();
		tprofile = System.currentTimeMillis() + "_" + tprofile;
		
		String savePath = "D:\\source\\spring\\Test201013\\src\\main\\webapp\\resources\\profile\\"+tprofile;
		if(!tfile.isEmpty()) {
			tfile.transferTo(new File(savePath));
		}
		
		String tid = (String)session.getAttribute("loginId");
		testtwo.setTid(tid);
		
		testtwo.setTprofile(tprofile);
		
		int updateResult = testtwoDAO.memberUpdateProcess(testtwo);
		if(updateResult > 0) {
			mav.setViewName("membert/MemberMain");
		} else {
			mav.setViewName("membert/MemberUpdateProcessFail");
		}
		
		return mav;
	}

	public ModelAndView memberList() {
		mav = new ModelAndView();
		List<TestTwoDTO> memberList = new ArrayList<TestTwoDTO>();
		memberList = testtwoDAO.memberList();
		mav.addObject("memberList", memberList);
		mav.setViewName("membert/MemberList");
		return mav;
	}

	public ModelAndView memberView(String tid) {
		mav = new ModelAndView();
		TestTwoDTO member = new TestTwoDTO();
		
		member = testtwoDAO.memberView(tid);
		mav.addObject("memberView", member);
		mav.setViewName("membert/MemberView");
		return mav;
	}

	public TestTwoDTO memberViewAjax(String tid) {
		TestTwoDTO memberView = testtwoDAO.memberView(tid);
		return memberView;
	}

	public ModelAndView memberDelete(String tid) {
		mav = new ModelAndView();
		int deleteResult = testtwoDAO.memberDelete(tid);
		
		if(deleteResult > 0) {
			mav.setViewName("redirect:/memberlist");
		} else {
			mav.setViewName("MemberDeleteFail");
		}
		return mav;
	}

	public ModelAndView kakaoLogin(JsonNode profile) {
		mav = new ModelAndView();
		String kakaoId = profile.get("id").asText();
		String loginId = testtwoDAO.kakaoLogin(kakaoId);
		
		session.setAttribute("loginId", loginId);
		mav.setViewName("membert/MemberMain");
		return mav;
	}

	public ModelAndView naverLogin(String profile) throws ParseException {
		mav = new ModelAndView();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String)userInfo.get("id");
		String loginId = testtwoDAO.naverLogin(naverId);
		session.setAttribute("loginId", loginId);
		mav.setViewName("membert/MemberMain");
		return mav;
	}
	
	public String idOverlap(String tid) {
		String checkResult = testtwoDAO.idOverlap(tid);
		String resultMsg = null;
		if(checkResult == null) {
			resultMsg = "OK";
		} else {
			resultMsg = "NO";
		}
		return resultMsg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
